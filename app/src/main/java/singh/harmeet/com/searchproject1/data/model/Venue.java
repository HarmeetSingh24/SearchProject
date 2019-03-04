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
public abstract class Venue implements Parcelable {

    @SerializedName("display_location")
    public abstract String display_location();

    public static TypeAdapter<Venue> typeAdapter(Gson gson){
        return new $AutoValue_Venue.GsonTypeAdapter(gson);
    }
}
