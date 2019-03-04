package singh.harmeet.com.searchproject1.di.component;

import dagger.Component;
import singh.harmeet.com.searchproject1.activity.DetailActivity;
import singh.harmeet.com.searchproject1.di.module.DetailActivityModule;
import singh.harmeet.com.searchproject1.di.scope.DetailActivityScope;

/**
 * Created by harmeet.assi on 4/16/2018.
 */

@DetailActivityScope
@Component(dependencies = AppComponent.class,modules = DetailActivityModule.class)
public interface DetailActivityComponent {
    void injectDetailActivity(DetailActivity detailActivity);
}