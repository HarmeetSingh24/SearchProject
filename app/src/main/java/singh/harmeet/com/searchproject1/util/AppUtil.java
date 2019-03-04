package singh.harmeet.com.searchproject1.util;

import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by harmeet.assi on 4/11/2018.
 */

public final class AppUtil {

    public static int convertoPixels(int dp){
        float desity = Resources.getSystem().getDisplayMetrics().density;
        return Math.round(dp*desity);
    }

    public static String convertDate(String date){
        try {
            DateFormat new_format = new SimpleDateFormat("EEE,dd MMM yyyy hh:mm a", Locale.ENGLISH);
            DateFormat old_format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
            return new_format.format(old_format.parse(date));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //show alert messages
    public static void showAlertMessage(String message,Context context) {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

    //to check internet connectivity
    public static boolean hasInternet(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo!=null && networkInfo.isConnectedOrConnecting();
    }
}
