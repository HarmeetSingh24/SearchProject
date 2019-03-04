package singh.harmeet.com.searchproject1.di.module;

import dagger.Module;
import dagger.Provides;
import singh.harmeet.com.searchproject1.view.DetailView;

/**
 * Created by harmeet.assi on 4/10/2018.
 */

@Module
public class DetailActivityModule {

    private DetailView detailView;


    public DetailActivityModule(DetailView detailView){
        this.detailView = detailView;
    }

    @Provides
    DetailView detailActivity(){
        return detailView;
    }
}
