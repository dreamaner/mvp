package com.android.mvp.cache;

import android.content.Context;
import android.content.SharedPreferences;




/**
 * Created by Dreamaner on 2017/5/15.
 */

public class PrefsUtils {

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    private static PrefsUtils instance;

    private PrefsUtils(Context context,String fileName) {
        sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public static PrefsUtils getInstance(Context context,String fileName) {
        if (instance == null) {
            synchronized (PrefsUtils.class) {
                if (instance == null) {
                    instance = new PrefsUtils(context.getApplicationContext(),fileName);
                }
            }
        }
        return instance;
    }

    public void remove(String key) {
        editor.remove(key);
        editor.apply();
    }

    public boolean contains(String key) {
        return sharedPreferences.contains(key);
    }

    public void clear() {
        editor.clear().apply();
    }

    public void putInt(String key, int value) {
        editor.putInt(key, value);
        editor.apply();
    }

    public int getInt(String key, int defValue) {
        return sharedPreferences.getInt(key, defValue);
    }

    public void putLong(String key, Long value) {
        editor.putLong(key, value);
        editor.apply();
    }

    public long getLong(String key, long defValue) {
        return sharedPreferences.getLong(key, defValue);
    }


    public void putBoolean(String key, Boolean value) {
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBoolean(String key, boolean defValue) {
        return sharedPreferences.getBoolean(key, defValue);
    }


    public void putString(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key, String defValue) {
        return sharedPreferences.getString(key, defValue);
    }


    public Object get(String key) {
        return null;
    }


    public void put(String key, Object value) {

    }
}
