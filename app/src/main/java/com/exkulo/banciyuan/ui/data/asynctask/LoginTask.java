package com.exkulo.banciyuan.ui.data.asynctask;

import android.app.DownloadManager;
import android.os.AsyncTask;
import android.os.Bundle;

import com.exkulo.banciyuan.ui.utils.L;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

import okio.BufferedSink;

/**
 * Created by Exkulo on 2015/10/12.
 */
public class LoginTask extends AsyncTask<Bundle, Integer, Bundle>{

    @Override
    protected Bundle doInBackground(Bundle... params) {
        OkHttpClient sClient = new OkHttpClient();
        RequestBody sBody = new FormEncodingBuilder()
                .add("email", "hhh132@126.com")
                .add("password", "zzwbili3470.")
                .add("remember", "1")
                .build();
        Request sRequest = new Request.Builder().url("http://bcy.net/public/dologin")
                .post(sBody).build();
        try {
            Response sResponse = sClient.newCall(sRequest).execute();
            L.i(sResponse.headers().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
