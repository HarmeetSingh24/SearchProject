package singh.harmeet.com.searchproject1.di.module;

import android.content.Context;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import singh.harmeet.com.searchproject1.di.scope.ApplciationContext;
import singh.harmeet.com.searchproject1.di.scope.ApplicationScope;

/**
 * Created by harmeet.assi on 4/11/2018.
 */

@Module(includes = {AppModule.class,NetworkModule.class})
public class PicassoModule {

    @Provides
    @ApplicationScope
    public OkHttp3Downloader provideOkHttp3Downloader(OkHttpClient okHttpClient){
        return new OkHttp3Downloader(okHttpClient);
    }

    @Provides
    @ApplicationScope
    public Picasso picasso(@ApplciationContext Context context, OkHttp3Downloader okHttp3Downloader){
        return new Picasso.Builder(context)
                .downloader(okHttp3Downloader)
                .build();
    }
}
