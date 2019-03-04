package singh.harmeet.com.searchproject1.data.network;

import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;


/**
 * Created by harmeet.assi on 4/10/2018.
 */

@GsonTypeAdapterFactory
public abstract class AutoValueGsonFactory implements TypeAdapterFactory {
    public static TypeAdapterFactory create(){
        return new AutoValueGson_AutoValueGsonFactory();
    }
}
