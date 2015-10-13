package com.exkulo.banciyuan.ui.utils;

import android.util.Log;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by exkulo on 9/16/2015.
 */
public class JsoupUtil {

    public static Document getDocument(String uri) {
        Document document = null;
        try {
            document = Jsoup.connect(uri)
                            .userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.93 Safari/537.36")
                            .timeout(5000)
                            .get();
        } catch (IOException e) {
           L.e(e);
        }
        return document;
    }

}
