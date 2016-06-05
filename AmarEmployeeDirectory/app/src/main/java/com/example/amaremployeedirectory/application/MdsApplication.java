package com.example.amaremployeedirectory.application;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;

import com.example.amaremployeedirectory.database.BaseDbHelper;
import com.example.amaremployeedirectory.database.MdsDBInitializer;

/**
 * Created by Amar on 6/4/2016.
 */

public class MdsApplication extends BaseApplication{
    private static final String PREFS_NAME = "com.mds.preferences";
    private static Context appContext;

    public static Context getAppContext() {
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext=this;
        getWritableDbInstance();
    }

    public SQLiteDatabase getWritableDbInstance() {
        if( mWritableDatabase == null ) {
            BaseDbHelper dbHelper = new BaseDbHelper(this,new MdsDBInitializer());
            mWritableDatabase = dbHelper.getWritableDatabase();
        }
        return mWritableDatabase;
    }

    public static String getLoggedUserId() {
        SharedPreferences settings = getAppContext().getSharedPreferences(PREFS_NAME, 0);
        return settings.getString("unreadNotifications", null);
    }
    public static void setLoggedUserId(String count)
    {
        SharedPreferences settings = getAppContext().getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("unreadNotifications", count);
        editor.commit();

    }
}
