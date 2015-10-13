package com.exkulo.banciyuan.ui.utils;

import android.net.Uri;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Callback;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

/**
 * Created by exkulo on 9/17/2015.
 */
public class ImageLoader {

    //private static LruCache mLruCache = new LruCache(24000);

    public static void show(ImageView iv, String uri) {
        //Picasso p = new Picasso.Builder(iv.getContext()).memoryCache(mLruCache).build();
        show(iv, uri, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }
        });

    }

    public static void showByGlide(ImageView iv, String uri) {
        Glide.with(iv.getContext()).load(uri).into(iv);
    }

    public static void show(ImageView iv, String uri, Callback callback) {
        Picasso.with(iv.getContext()).load(uri).into(iv, callback);
    }
}
