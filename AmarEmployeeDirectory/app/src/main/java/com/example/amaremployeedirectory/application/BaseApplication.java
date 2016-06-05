package com.example.amaremployeedirectory.application;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.amaremployeedirectory.database.BaseDbHelper;
import com.example.amaremployeedirectory.util.MdsLog;


/**
 * This class holds some application-global instances.
 */
public class BaseApplication extends Application {
    private final String LOG_TAG = "BaseApplication";

    protected SQLiteDatabase mWritableDatabase;
    private static BaseApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);

    }

    /**
     * Get the database instance.
     *
     * @return mWritableDatabase
     */
    public SQLiteDatabase getWritableDbInstance() {
        if (mWritableDatabase == null) {
            BaseDbHelper dbHelper = new BaseDbHelper(this);
            mWritableDatabase = dbHelper.getWritableDatabase();
        }
        return mWritableDatabase;
    }

    @Override
    public void onTerminate() {
        MdsLog.i(LOG_TAG, "onTerminate()");
        if (mWritableDatabase != null) {
            mWritableDatabase.close();
        }
        super.onTerminate();
    }

    public static BaseApplication getInstance() {
        return mInstance;
    }

    protected static void setInstance(BaseApplication mInstance) {
        BaseApplication.mInstance = mInstance;
    }
}
