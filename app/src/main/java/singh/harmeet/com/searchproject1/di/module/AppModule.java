package singh.harmeet.com.searchproject1.di.module;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import singh.harmeet.com.searchproject1.di.scope.ApplciationContext;
import singh.harmeet.com.searchproject1.di.scope.ApplicationScope;

/**
 * Created by harmeet.assi on 4/10/2018.
 */

@Module
public class AppModule {
    private Application application;

    public AppModule(Application application){
        this.application = application;
    }

    @Provides
    @ApplicationScope
    @ApplciationContext
    public Context getContext(){
        return application;
    }

}
