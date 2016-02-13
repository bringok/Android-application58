package it.bitesrl.univaq.corso.cityfinal.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mattia on 04/02/16.
 */
public class UtilsPreference {

    private static final String PREFERENCES = "pref";


    public static final String KEY_FIRSTTIME = "firsttime";


    public static void save(Context context, String key, boolean value){
        SharedPreferences pref = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void savecity(Context context, String key, String value){
        SharedPreferences pref = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static boolean load(Context context, String key, boolean defaultValue){
        SharedPreferences pref = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        return pref.getBoolean(key, defaultValue);
    }

    public static String loadcity(Context context, String key, String defaultValue){
        SharedPreferences pref = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        return pref.getString(key, defaultValue);
    }


}
