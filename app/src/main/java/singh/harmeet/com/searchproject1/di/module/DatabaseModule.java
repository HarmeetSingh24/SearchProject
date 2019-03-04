package singh.harmeet.com.searchproject1.di.module;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.framework.FrameworkSQLiteOpenHelperFactory;
import android.content.Context;

import com.squareup.sqlbrite3.BriteDatabase;
import com.squareup.sqlbrite3.SqlBrite;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import singh.harmeet.com.searchproject1.data.Callback;
import singh.harmeet.com.searchproject1.di.scope.ApplciationContext;
import singh.harmeet.com.searchproject1.di.scope.ApplicationScope;

/**
 * Created by harmeet.assi on 4/15/2018.
 */

@Module(includes = PicassoModule.class)
public class DatabaseModule {

    @ApplicationScope
    @Provides
    SqlBrite getSqlBrite(){
        return new SqlBrite.Builder().build();
    }

    @ApplicationScope
    @Provides
    BriteDatabase getDatabase(SqlBrite sqlBrite,@ApplciationContext Context context){
        SupportSQLiteOpenHelper.Configuration configuration = SupportSQLiteOpenHelper.Configuration.builder(context)
                .name("seat_geek.db")
                .callback(new Callback())
                .build();
        SupportSQLiteOpenHelper.Factory factory = new FrameworkSQLiteOpenHelperFactory();
        SupportSQLiteOpenHelper supportSQLiteOpenHelper = factory.create(configuration);
        return sqlBrite.wrapDatabaseHelper(supportSQLiteOpenHelper, Schedulers.io());
    }
}
