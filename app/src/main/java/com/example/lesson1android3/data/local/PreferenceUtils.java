package com.example.lesson1android3.data.local;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtils {
    private static SharedPreferences sharedPreferences;
    private final static String APP_PREF_NAME = "kg.geektech.lesson1";
    private final static String LOGIN_LINE = "login";
    private final static String PASSWORD_LINE = "password";

    public static void init(Context context) {
        sharedPreferences = context.getSharedPreferences(APP_PREF_NAME, Context.MODE_PRIVATE);
    }

    public static void saveLoginLine(String userName) {
        sharedPreferences.edit().putString(LOGIN_LINE, userName).apply();
    }

    public static String getLoginLine() {
        return sharedPreferences.getString(LOGIN_LINE, "");
    }

    public static void savePasswordLine(String userName) {
        sharedPreferences.edit().putString(PASSWORD_LINE, userName).apply();
    }

    public static String getPasswordLine() {
        return sharedPreferences.getString(PASSWORD_LINE, "");
    }

}
