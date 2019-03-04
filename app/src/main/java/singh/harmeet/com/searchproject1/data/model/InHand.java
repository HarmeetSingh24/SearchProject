package singh.harmeet.com.searchproject1.data.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import io.reactivex.annotations.Nullable;

/**
 * Created by harmeet.assi on 4/10/2018.
 */

@AutoValue
public abstract class InHand {

    @Nullable
    abstract String hands();

    public static TypeAdapter<InHand> typeAdapter(Gson gson){
        return new AutoValue_InHand.GsonTypeAdapter(gson);
    }
}
