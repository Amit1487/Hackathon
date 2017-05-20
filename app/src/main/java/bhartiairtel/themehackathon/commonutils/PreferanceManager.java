package bhartiairtel.themehackathon.commonutils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by B0089798 on 21 / May / 2017 , 3:45 AM.
 * bhartiairtel.themehackathon.commonutils
 * Hackathon
 */

public class PreferanceManager {


    public static void storeJson(Context context, String json) {
        SharedPreferences sharedpreferences = context.getSharedPreferences("my_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("json", json);
        editor.commit();
    }

    public static String getJson(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences("my_pref", Context.MODE_PRIVATE);
        String data = sharedPref.getString("json", "");
        return data;
    }

    public static void setLoginTrue(Context context) {
        SharedPreferences sharedpreferences = context.getSharedPreferences("my_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean("login", true);
        editor.commit();
    }

    public static boolean getLogin(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences("my_pref", Context.MODE_PRIVATE);
        boolean data = sharedPref.getBoolean("login", false);
        return data;
    }
}
