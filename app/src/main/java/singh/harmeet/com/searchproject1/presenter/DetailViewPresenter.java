package singh.harmeet.com.searchproject1.presenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import singh.harmeet.com.searchproject1.data.DbHelper;
import singh.harmeet.com.searchproject1.data.model.Event;
import singh.harmeet.com.searchproject1.view.DetailView;

/**
 * Created by harmeet.assi on 4/15/2018.
 */


public class DetailViewPresenter extends BasePresenter<DetailView> {

    private final DbHelper dbHelper;
    private CompositeDisposable compositeDisposable;
    @Inject
    DetailViewPresenter(DbHelper dbHelper){
        this.dbHelper = dbHelper;
    }

    @Override
    public void attachView(DetailView detailView){
        super.attachView(detailView);

        if(compositeDisposable == null){
            compositeDisposable = new CompositeDisposable();
        }


    }

    //this will load the event from the data received on tapping a particular event
    public void loadEvent(final Event event){
        if(event!=null){
            DetailViewPresenter.this.getMView().loadItems(event);
        }else{
            DetailViewPresenter.this.getMView().showError();
        }
        //check if a the event is made favorite previously
        compositeDisposable.add(
                dbHelper.getFavorite().filter(new Predicate<List<Event>>() {
            @Override
            public boolean test(List<Event> events) throws Exception {
                if(event!=null)
                                return true;
                else
                    return  false;
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Event>>() {
                    @Override
                    public void accept(List<Event> events) throws Exception {
                        for(Event eve:events) {
                            if (eve.id()==event.id()) {
                                DetailViewPresenter.this.getMView().showFavorite();
                                break;
                            }
                        }
                    }
                })
        );

        }

    public void addtoFav(final Event parcelableExtra) {

        compositeDisposable.add(
                dbHelper.setFavorite(parcelableExtra).
                        filter(new Predicate<Event>() {
                            @Override
                            public boolean test(Event event) throws Exception {
                                if(event!=null)
                                    return true;
                                else{

                                    return false;
                                }
                            }
                        })
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Event>() {
                    @Override
                    public void accept(Event event) throws Exception {
                        DetailViewPresenter.this.getMView().showFavorite();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //if there is constraint error then delete the item and show no favorite icon
                        throwable.printStackTrace();
                        dbHelper.deleteEvent((int)parcelableExtra.id());
                        DetailViewPresenter.this.getMView().hideFav();
                    }
                })
        );
    }
}
