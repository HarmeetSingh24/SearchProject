package singh.harmeet.com.searchproject1.data;

import com.squareup.sqlbrite3.BriteDatabase;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import singh.harmeet.com.searchproject1.data.model.Event;
import singh.harmeet.com.searchproject1.di.scope.ApplicationScope;

import static android.database.sqlite.SQLiteDatabase.CONFLICT_FAIL;

/**
 * Created by harmeet.assi on 4/15/2018.
 */

@ApplicationScope
public class DbHelper {

    @Inject
    BriteDatabase briteDatabase;

    @Inject
    DbHelper(){

    }

    public io.reactivex.Observable<Event> setFavorite(final Event event){
        return io.reactivex.Observable.defer(new Callable<ObservableSource<? extends Event>>() {
            @Override
            public ObservableSource<? extends Event> call() throws Exception {
                BriteDatabase.Transaction transaction = briteDatabase.newTransaction();

                try{
//                    briteDatabase.delete(Event.TABLE,null);
                    long entry = briteDatabase.insert(Event.TABLE,CONFLICT_FAIL,
                            new Event.Builder().id((int)event.id()).title(event.title()).build());
                    if(entry<0){
                        return io.reactivex.Observable.just(null);
                    }
                    transaction.markSuccessful();
                }finally {
                    transaction.end();
                }
                return io.reactivex.Observable.just(event);
            }
        });
    }

    public Observable<List<Event>> getFavorite(){
       return briteDatabase.createQuery(Event.TABLE,Callback.getValue)
                .mapToList(Event.map);
    }

    public void deleteEvent(int id){
        BriteDatabase.Transaction transaction = briteDatabase.newTransaction();
        try{
            briteDatabase.delete(Event.TABLE,Event.ID + " = ?", Integer.toString(id));
            transaction.markSuccessful();
        }finally {
            transaction.end();
        }

    }
}

