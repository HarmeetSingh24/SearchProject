package singh.harmeet.com.searchproject1.data.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import io.reactivex.annotations.Nullable;

/**
 * Created by harmeet.assi on 4/10/2018.
 */

@AutoValue
public abstract class Images implements Parcelable {

    @Nullable
    @SerializedName("huge")
    public abstract String huge();

    public static TypeAdapter<Images> typeAdapter(Gson gson){
        return new $AutoValue_Images.GsonTypeAdapter(gson);
    }
}
