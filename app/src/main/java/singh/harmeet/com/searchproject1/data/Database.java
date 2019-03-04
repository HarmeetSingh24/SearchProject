package singh.harmeet.com.searchproject1.data;

import android.database.Cursor;

/**
 * Created by harmeet.assi on 4/15/2018.
 */

public final class Database {
    public static String getString(Cursor cursor,String col){
        return cursor.getString(cursor.getColumnIndexOrThrow(col));
    }

    public  static int getInt(Cursor cursor,String col){
        return cursor.getInt(cursor.getColumnIndexOrThrow(col));
    }
}
