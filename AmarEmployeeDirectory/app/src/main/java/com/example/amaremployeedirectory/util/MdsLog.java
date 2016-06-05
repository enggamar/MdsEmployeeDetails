package com.example.amaremployeedirectory.util;

import android.util.Log;

public class MdsLog {
	private static boolean isLogDisable=false;

	/*
     * Send a {@link #WARN} log message and log the exception.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param tr An exception to log
     */
    public static int w(String tag, Throwable tr) {
    	if(isLogDisable)
    		return 0;
        return Log.w(tag,tr);
    }

    /**
     * Send an {@link #ERROR} log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static int e(String tag, String msg) {
    	if(isLogDisable)
    		return 0;
        return Log.e(tag,msg);
    }

    /**
     * Send a {@link #ERROR} log message and log the exception.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr An exception to log
     */
    public static int e(String tag, String msg, Throwable tr) {
    	if(isLogDisable)
    		return 0;
        return Log.e(tag, msg, tr);
    }

	public static int d(String tag, String string) {
		// TODO Auto-generated method stub
		if(isLogDisable)
    		return 0;
        return Log.d(tag,string);
	}

	public static int i(String tag, String msg) {
		// TODO Auto-generated method stub
		if(isLogDisable)
    		return 0;
        return Log.i(tag,msg);
	}

	public static int v(String tag, String msg) {
		// TODO Auto-generated method stub
		if(isLogDisable)
    		return 0;
        return Log.v(tag,msg);
	}

	public static int w(String tag, String msg) {
		// TODO Auto-generated method stub
		if(isLogDisable)
    		return 0;
        return Log.v(tag,msg);
	}
}
