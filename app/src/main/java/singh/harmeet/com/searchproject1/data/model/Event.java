package singh.harmeet.com.searchproject1.data.model;


import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Function;
import singh.harmeet.com.searchproject1.data.Database;


/**
 * Created by harmeet.assi on 4/10/2018.
 */

@AutoValue
public abstract class Event implements Parcelable {

    public static final String TABLE = "events";
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String VENUE = "venue";
    public static final String DATE_TIME = "date_time";
    public static final String PERFORMERS = "performers";

    @SerializedName("title")
    public abstract String title();

    @Nullable
    @SerializedName("datetime_local")
    public abstract String datetime_local();

    @Nullable
    @SerializedName("venue")
    public abstract Venue venue();

    @SerializedName("id")
    public abstract long id();

    @Nullable
    @SerializedName("performers")
    public abstract List<Performer> performers();

    public static TypeAdapter<Event> typeAdapter(Gson gson){
        return new $AutoValue_Event.GsonTypeAdapter(gson);
    }

    public static final Function<Cursor,Event> map =new Function<Cursor, Event>() {

        @Override
        public Event apply(Cursor cursor) {
            int id = Database.getInt(cursor,ID);
            String title = Database.getString(cursor,TITLE);
            return new AutoValue_Event(title,null,null,(long)id,null);
        }
    };

    public static final class Builder {
        private final ContentValues values = new ContentValues();

        public Builder id(int id ){
            values.put(ID,id);
            return this;
        }
        public Builder title(String title){
            values.put(TITLE,title);
            return this;
        }

        public ContentValues build(){
            return values;
        }
    }
}
