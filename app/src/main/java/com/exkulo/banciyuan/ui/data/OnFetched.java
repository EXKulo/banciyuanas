package com.exkulo.banciyuan.ui.data;

/**
 * Created by exkulo on 9/17/2015.
 */
public interface OnFetched {

    public void succeed(Object obj);

    public void fail(String msg, int status);

}
