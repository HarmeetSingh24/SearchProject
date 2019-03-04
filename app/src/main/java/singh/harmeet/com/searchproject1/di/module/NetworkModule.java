package singh.harmeet.com.searchproject1.di.module;

import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import singh.harmeet.com.searchproject1.data.network.AutoValueGsonFactory;
import singh.harmeet.com.searchproject1.data.network.SearchApiInterface;
import singh.harmeet.com.searchproject1.di.scope.ApplicationScope;
import singh.harmeet.com.searchproject1.util.Constants;

/**
 * Created by harmeet.assi on 4/10/2018.
 */

@Module
public class NetworkModule {

    @Provides
    @ApplicationScope
    OkHttpClient provideOkHttp(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .connectTimeout(Constants.timeout, TimeUnit.MILLISECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    @Provides
    @ApplicationScope
    Retrofit retrofit(OkHttpClient okHttpClient){
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(new GsonBuilder().registerTypeAdapterFactory(AutoValueGsonFactory.create()).create());
        return new Retrofit
                .Builder()
                .baseUrl(Constants.end_point)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @ApplicationScope
    SearchApiInterface apiInterface(Retrofit retrofit){
        return retrofit.create(SearchApiInterface.class);
    }
}
