package singh.harmeet.com.searchproject1;

import android.app.Application;
import android.content.Context;

import com.squareup.picasso.Picasso;

import singh.harmeet.com.searchproject1.di.component.AppComponent;
import singh.harmeet.com.searchproject1.di.component.DaggerAppComponent;
import singh.harmeet.com.searchproject1.di.module.AppModule;

/**
 * Created by harmeet.assi on 4/11/2018.
 */

public class MainApplication extends Application {
    AppComponent appComponent ;
    Picasso picasso;

    @Override
    public void onCreate(){
        super.onCreate();

    }

    public static MainApplication get(Context context){
        return (MainApplication) context.getApplicationContext();
    }

    public AppComponent getAppComponent(){
        if(appComponent==null){
            appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        }
        return appComponent;
    }
    public Picasso getPicasso(){
        return appComponent.picasso();
    }
}
