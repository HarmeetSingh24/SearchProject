package singh.harmeet.com.searchproject1.data.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;


/**
 * Created by harmeet.assi on 4/10/2018.
 */

@AutoValue
public abstract class Performer implements Parcelable{

    @SerializedName("images")
    public abstract Images images();

    public static TypeAdapter<Performer> typeAdapter(Gson gson){
        return new $AutoValue_Performer.GsonTypeAdapter(gson);
    }
}
