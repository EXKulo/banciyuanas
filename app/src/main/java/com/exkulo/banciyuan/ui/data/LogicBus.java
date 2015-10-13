package com.exkulo.banciyuan.ui.data;

import android.os.Bundle;
import com.exkulo.banciyuan.ui.data.asynctask.GetHotestPostTask;
import com.exkulo.banciyuan.ui.data.asynctask.GetPostDetailTask;
import com.exkulo.banciyuan.ui.model.HotestPostDigest;
import java.util.List;

/**
 * Created by exkulo on 9/16/2015.
 */
public class LogicBus {

    public static List<HotestPostDigest> getHotestPost(OnFetched onFetched) {
        new GetHotestPostTask(onFetched).execute(new Bundle());
        return null;
    }

    public static void getPostDetail(String url, OnFetched onFetched) {
        new GetPostDetailTask(onFetched).execute(url);
    }
}
