package com.exkulo.banciyuan.ui.data.asynctask;

import android.os.AsyncTask;

import com.exkulo.banciyuan.ui.data.OnFetched;
import com.exkulo.banciyuan.ui.utils.FileUtil;
import com.exkulo.banciyuan.ui.utils.L;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by exkulo on 10/3/2015.
 */
public class SavePicTask extends AsyncTask<String, Integer, Boolean>{

    private OnFetched mOnFetched;

    public SavePicTask(OnFetched onFetched) {
        if (onFetched == null) throw new IllegalStateException("onFetched can not be null!");
        mOnFetched = onFetched;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(params[0].replace("/w650","")).build();
        try {
            Response response = client.newCall(request).execute();
            byte[] inputStream = response.body().bytes();
            FileUtil.saveImage(inputStream, params[1]);
            return true;
        } catch (IOException e) {
            L.e(e);
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        mOnFetched.succeed(aBoolean);
    }
}
