package singh.harmeet.com.searchproject1.di.component;

import dagger.Component;
import singh.harmeet.com.searchproject1.activity.MainActivity;
import singh.harmeet.com.searchproject1.di.module.MainActivityModule;
import singh.harmeet.com.searchproject1.di.scope.MainActivityScope;

/**
 * Created by harmeet.assi on 4/11/2018.
 */

@MainActivityScope
@Component(dependencies = AppComponent.class,modules = MainActivityModule.class)
public interface MainActivityComponent {
    void injectMainActivity(MainActivity mainActivity);
}
