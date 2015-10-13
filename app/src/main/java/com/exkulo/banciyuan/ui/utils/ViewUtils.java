package com.exkulo.banciyuan.ui.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by exkulo on 9/15/2015.
 */
public class ViewUtils {

    private static Toast mToast;

    /**
     * Toast，而且不会堆积
     */
    public static void t(Context context, String msg) {
        dismissT();
        mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        mToast.show();
    }

    /**
     * 让toast消失
     */
    public static void dismissT() {
        if (mToast != null) {
            mToast.cancel();
        }
    }

    public static int getDiviceWidthPixis() {
        Context appContext = ContextAccessor.getContextAccessor().getContext();
        int mScreenWidth;
        WindowManager wm = (WindowManager) appContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mScreenWidth = outMetrics.widthPixels;
        return mScreenWidth;
    }

    public static int dip2px(float dpValue) {
        Context context = ContextAccessor.getContextAccessor().getContext();
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
