package singh.harmeet.com.searchproject1.data.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 * Created by harmeet.assi on 4/10/2018.
 */

@AutoValue
public abstract class Result {

    @SerializedName("in_hand")
    public abstract InHand inhand();

    @SerializedName("events")
    public abstract List<Event> events();

    @SerializedName("meta")
    public abstract Meta meta();

    public static TypeAdapter<Result> typeAdapter(Gson gson){
        return new AutoValue_Result.GsonTypeAdapter(gson);
    }
}
