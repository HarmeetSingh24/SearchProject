package singh.harmeet.com.searchproject1.data.network;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import singh.harmeet.com.searchproject1.data.model.Event;
import singh.harmeet.com.searchproject1.data.model.Result;
import singh.harmeet.com.searchproject1.di.scope.ApplicationScope;
import singh.harmeet.com.searchproject1.util.Constants;

/**
 * Created by harmeet.assi on 4/11/2018.
 */

@ApplicationScope
public class DataHelper {

    private final SearchApiInterface apiInterface;

    @Inject
    DataHelper(SearchApiInterface apiInterface){
        this.apiInterface = apiInterface;
    }

    public io.reactivex.Observable<List<Event>> getRequest(String query, String page){
        return apiInterface.getQueryResult(Constants.client_id, Constants.client_secret,query,page)
                .concatMap(new Function<Result, ObservableSource<? extends List<Event>>>() {
                    @Override
                    public ObservableSource<? extends List<Event>> apply(Result result) throws Exception {
                        return io.reactivex.Observable.just(result.events());
                    }
                })
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends List<Event>>>() {
                    @Override
                    public ObservableSource<? extends List<Event>> apply(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        return io.reactivex.Observable.empty();
                    }
                });
    }
}
