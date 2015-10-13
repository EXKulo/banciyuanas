package com.exkulo.banciyuan.ui.utils;

import android.util.Log;

/**
 * Created by exkulo on 9/16/2015.
 */
public class L {

    private static boolean debug = true;

    public static String TAG = "exkuloTag";

    public static void i(String msg) {
        if (debug) {
            Log.i(TAG, (msg == null ? "null" : msg));
        }
    }

    public static void i(Throwable e) {
        if (debug) {
            Log.i(TAG, Log.getStackTraceString(e));
        }
    }

    public static void i(Object obj) {
        i(obj.toString());
    }

    public static void i(int integer) {
        i(integer + "");
    }

    public static void e(String msg) {
        if(debug) {
            Log.e(TAG, msg);
        }
    }

    public static void e(Throwable e) {
        if(debug) {
            Log.e(TAG, Log.getStackTraceString(e));
        }
    }
}
