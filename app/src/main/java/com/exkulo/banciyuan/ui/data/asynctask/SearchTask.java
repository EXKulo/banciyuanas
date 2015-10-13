package com.exkulo.banciyuan.ui.data.asynctask;

import android.os.AsyncTask;
import android.os.Bundle;

import com.exkulo.banciyuan.ui.utils.L;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Exkulo on 2015/10/12.
 */
public class SearchTask extends AsyncTask<String, Integer, Bundle> {

    @Override
    protected Bundle doInBackground(String... params) {
        OkHttpClient sClient = new OkHttpClient();
        String sKey = "哈哈哈哈哈";
        String sUrlKey = null;
        try {
            sUrlKey = URLEncoder.encode(sKey, "utf-8");
            L.i(sUrlKey);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        String sUrl = "http://bcy.net/search/coser?k=" + sUrlKey;
        Request sRequest = new Request.Builder().url(sUrl).build();
        try {
            Response sResponse = sClient.newCall(sRequest).execute();
            L.i(sResponse.body().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
