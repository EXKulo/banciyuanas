package com.exkulo.banciyuan.ui.utils;

/**
 * Created by exkulo on 9/17/2015.
 */
public class StringUtils {

    public static String getFrontPart(String src) {
        int spaceIndex = src.indexOf(" ");
        String front = src.substring(0, spaceIndex);
        return front;
    }

}
