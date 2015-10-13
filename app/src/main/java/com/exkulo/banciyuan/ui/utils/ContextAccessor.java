package com.exkulo.banciyuan.ui.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * Created by exkulo on 10/3/2015.
 */
public class ContextAccessor {

    private Context mContext;

    private static ContextAccessor mContextAccessor;

    private ContextAccessor(Context context) {
        mContext = context.getApplicationContext();
    }

    public static void init(Context context) {
        if (mContextAccessor == null) {
            mContextAccessor = new ContextAccessor(context);
        }
    }

    private static void checkIfInit() {
        if (mContextAccessor == null) {
            throw new IllegalStateException("还没有初始化！请在自定义的Application中进行init()操作！");
        }
    }

    public static ContextAccessor getContextAccessor() {
        checkIfInit();
        return mContextAccessor;
    }

    public Context getContext() {
        return mContextAccessor.mContext;
    }

    public int getColor(int id) {
        checkIfInit();
        return mContextAccessor.mContext.getResources().getColor(id);
    }

    public float getDimen(int id) {
        checkIfInit();
        return mContextAccessor.mContext.getResources().getDimension(id);
    }

    public Drawable getDrawable(int id) {
        checkIfInit();
        return mContextAccessor.mContext.getResources().getDrawable(id);
    }
}
