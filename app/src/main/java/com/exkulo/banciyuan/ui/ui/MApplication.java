package com.exkulo.banciyuan.ui.ui;

import android.app.Application;
import com.exkulo.banciyuan.ui.utils.ContextAccessor;

/**
 * Created by exkulo on 10/3/2015.
 */
public class MApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ContextAccessor.init(this);
    }
}
