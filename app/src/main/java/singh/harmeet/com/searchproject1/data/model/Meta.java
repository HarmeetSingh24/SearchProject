package singh.harmeet.com.searchproject1.data.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import io.reactivex.annotations.Nullable;

/**
 * Created by harmeet.assi on 4/10/2018.
 */

@AutoValue
public abstract class Meta {

    @SerializedName("per_page")
    public abstract int per_page();

    @SerializedName("took")
    public abstract int took();

    @Nullable
    @SerializedName("geolocation")
    public abstract String geolocation();

    @SerializedName("total")
    public abstract int total();

    @SerializedName("page")
    public abstract int page();

    public static TypeAdapter<Meta> typeAdapter(Gson gson){
        return new AutoValue_Meta.GsonTypeAdapter(gson);
    }
}
