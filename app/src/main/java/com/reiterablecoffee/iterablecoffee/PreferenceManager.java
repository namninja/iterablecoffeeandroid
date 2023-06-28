package com.reiterablecoffee.iterablecoffee;
import android.content.Context;
import android.content.SharedPreferences;


public class PreferenceManager {
    private static final String PREF_NAME = "MyPrefs";
    private static final String GLOBAL_VARIABLE_KEY = "myGlobalVariable";

    private SharedPreferences sharedPreferences;
    private static PreferenceManager instance;

    private PreferenceManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized PreferenceManager getInstance(Context context) {
        if (instance == null) {
            instance = new PreferenceManager(context);
        }
        return instance;
    }

    public void setMyGlobalVariable(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(GLOBAL_VARIABLE_KEY, token);
        editor.apply();
    }

    public String getMyGlobalVariable() {
        return sharedPreferences.getString(GLOBAL_VARIABLE_KEY, null);
    }
}
