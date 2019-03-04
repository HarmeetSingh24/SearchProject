package singh.harmeet.com.searchproject1.di.component;

import com.squareup.picasso.Picasso;

import dagger.Component;
import singh.harmeet.com.searchproject1.data.DbHelper;
import singh.harmeet.com.searchproject1.data.network.DataHelper;
import singh.harmeet.com.searchproject1.di.module.DatabaseModule;
import singh.harmeet.com.searchproject1.di.scope.ApplicationScope;

/**
 * Created by harmeet.assi on 4/11/2018.
 */

@ApplicationScope
@Component(modules = DatabaseModule.class)
public interface AppComponent {

    Picasso picasso();
    DataHelper dataHelper();
    DbHelper dbHelper();
}
