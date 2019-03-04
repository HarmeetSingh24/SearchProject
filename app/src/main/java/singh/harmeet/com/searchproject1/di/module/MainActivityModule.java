package singh.harmeet.com.searchproject1.di.module;

import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import singh.harmeet.com.searchproject1.activity.MainActivity;
import singh.harmeet.com.searchproject1.adapter.EventAdapter;

/**
 * Created by harmeet.assi on 4/10/2018.
 */

@Module
public class MainActivityModule {

    private MainActivity mainActivity;


    public MainActivityModule(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Provides
    MainActivity mainActivity(){
        return mainActivity;
    }

    @Provides
    EventAdapter provideEventAdapter(Picasso picasso){
        return new EventAdapter(mainActivity,picasso);
    }
}
