package singh.harmeet.com.searchproject1.presenter;

import android.text.TextUtils;

import com.jakewharton.rxbinding2.support.v7.widget.RecyclerViewScrollEvent;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import singh.harmeet.com.searchproject1.data.model.Counter;
import singh.harmeet.com.searchproject1.data.model.Event;
import singh.harmeet.com.searchproject1.data.network.DataHelper;
import singh.harmeet.com.searchproject1.view.MainView;

/**
 * Created by harmeet.assi on 4/11/2018.
 */

public class MainViewPresenter extends BasePresenter<MainView> {

    private final DataHelper dataHelper;
    private CompositeDisposable compositeDisposable;
    private PublishSubject<Counter> subject;
    //to invoke the calls to seat geek during subscription
    private Counter counter;

    @Inject
    MainViewPresenter(DataHelper dataHelper,Counter counter){
        this.dataHelper = dataHelper;
        this.counter = counter;
    }

    @Override
    public void attachView(MainView mView) {
        super.attachView(mView);
        if(compositeDisposable==null){
            compositeDisposable = new CompositeDisposable();
        }
        subject = PublishSubject.create();

        compositeDisposable.add(subject.switchMap(new Function<Counter, ObservableSource<List<Event>>>() {
            @Override
            public ObservableSource<List<Event>> apply(Counter counter) throws Exception {
                return MainViewPresenter.this.sendRequest(counter);
            }
        })
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<Event>>() {
            @Override
            public void accept(List<Event> events) throws Exception {
                counter.updateList(events);
                if (events.isEmpty()) {
                    MainViewPresenter.this.getMView().Error();
                } else {
                    MainViewPresenter.this.getMView().showResults(counter.getEventList());
                }
            }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    throwable.printStackTrace();
                    MainViewPresenter.this.getMView().Error();
                }
            }));
    }

    //destroy the disposable when view is removed
    @Override
    public void detachView() {
        super.detachView();
        if(compositeDisposable!=null){
            compositeDisposable.clear();
        }
    }

    //search the queries and update the counter
    public void searchEvent(Observable<CharSequence> observable, final boolean hasInternet){
        checkmView();
        compositeDisposable.add(
                observable.filter(new Predicate<CharSequence>() {
                    @Override
                    public boolean test(CharSequence charSequence) throws Exception {
                        if(hasInternet) return !TextUtils.isEmpty(charSequence);
                        else {
                            MainViewPresenter.this.getMView().InternetError();
                            return false;
                        }
                    }
                })
                .throttleLast(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .map(new Function<CharSequence, Counter>() {
                    @Override
                    public Counter apply(CharSequence charSequence) throws Exception {
                        return new Counter(charSequence.toString().trim().replace(" +"," ").replace(" ","+"));
                    }
                })
                .doOnNext(new Consumer<Counter>() {
                    @Override
                    public void accept(Counter counter) throws Exception {
                        MainViewPresenter.this.counter = counter;
                    }
                })
                .switchMap(new Function<Counter, ObservableSource<List<Event>>>() {
                    @Override
                    public ObservableSource<List<Event>> apply(Counter counter) throws Exception {
                        return MainViewPresenter.this.sendRequest(counter);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Event>>() {
                    @Override
                    public void accept(List<Event> events) throws Exception {
                        counter.updateList(events);
                        MainViewPresenter.this.getMView().showResults(events);
                    }
                },new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable t) throws Exception {
                        t.printStackTrace();
                    }
                }
        ));

    }

    //send the request to seat geek api
    private ObservableSource<List<Event>> sendRequest(Counter counter) {
        return dataHelper.getRequest(counter.getQuery(),counter.getCounter()).subscribeOn(Schedulers.io());
    }

    //to observe the scrolling of the results of the user
    public void scrollEvents(Observable<RecyclerViewScrollEvent> observable) {
        checkmView();
        compositeDisposable.add(
                observable.filter(new Predicate<RecyclerViewScrollEvent>() {
                    @Override
                    public boolean test(RecyclerViewScrollEvent recyclerViewScrollEvent) throws Exception {
                        return MainViewPresenter.this.getMView().itemsVisible() >=MainViewPresenter.this.counter.getListCount();
                    }
                })
                .throttleFirst(1,TimeUnit.SECONDS,AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RecyclerViewScrollEvent>() {
                    @Override
                    public void accept(RecyclerViewScrollEvent recyclerViewScrollEvent) throws Exception {
                        checkmView();
                        subject.onNext(counter);
                    }
                })
        );
    }
}
