package singh.harmeet.com.searchproject1.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;

import singh.harmeet.com.searchproject1.data.model.Event;

/**
 * Created by harmeet.assi on 4/15/2018.
 */

public final class Callback extends SupportSQLiteOpenHelper.Callback{

    private static final int DB_VERSION = 1;
    /**
     * Creates a new Callback to get database lifecycle events.
     *
     * @param version The version for the database instance. See {@link #version}.
     */
    public Callback() {
        super(DB_VERSION);
    }

    private static final String initialize = "CREATE TABLE "+ Event.TABLE +" (" + Event.ID +" INTEGER NOT NULL PRIMARY KEY, " + Event.TITLE + " TEXT )";

     static final String getValue = "SELECT * FROM " + Event.TABLE;
    @Override
    public void onCreate(SupportSQLiteDatabase db) {
        db.execSQL(initialize);
    }

    @Override
    public void onUpgrade(SupportSQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
