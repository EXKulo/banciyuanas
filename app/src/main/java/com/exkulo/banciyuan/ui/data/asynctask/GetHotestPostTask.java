package com.exkulo.banciyuan.ui.data.asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import com.exkulo.banciyuan.ui.data.OnFetched;
import com.exkulo.banciyuan.ui.model.HomePageEntity;
import com.exkulo.banciyuan.ui.model.HotestPostDigest;
import com.exkulo.banciyuan.ui.model.PostDigest;
import com.exkulo.banciyuan.ui.utils.JsoupUtil;
import com.exkulo.banciyuan.ui.utils.L;
import com.exkulo.banciyuan.ui.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by exkulo on 9/16/2015.
 */
public class GetHotestPostTask extends AsyncTask<Bundle, Integer, HomePageEntity> {

    private OnFetched mOnFetched;

    public GetHotestPostTask(OnFetched onFetched) {
        if (onFetched == null) throw new IllegalStateException("onFetched can not be null!");
        mOnFetched = onFetched;
    }

    @Override
    protected HomePageEntity doInBackground(Bundle... params) {

        Document document = JsoupUtil.getDocument("http://bcy.net/start");

        if (document != null) {
            HomePageEntity entity = new HomePageEntity();
            List<HotestPostDigest> digests = new ArrayList<HotestPostDigest>();

            //class = grid__inner gallery gallery--5
            Elements board = document.select("ul.grid__inner.gallery.gallery--5");
            Element firstBorad = (board.size() == 0 ? null : board.get(0));
            //work-thumbnail js-img-error work-thumbnail--top work-thumbnail--small
            Elements arts = firstBorad.getElementsByClass("work-thumbnail")
                                      .addClass("js-img-error")
                                      .addClass("work-thumbnail--top")
                                      .addClass("work-thumbnail--small");
            for (Element art : arts) {
                HotestPostDigest digest = new HotestPostDigest();
                Element a = art.getElementsByTag("a").get(0);
                //超链接那里的信息
                String href = a.attr("href");
                String title = a.attr("title");
                digest.setPostUri("http://bcy.net" + href);
                Element img = art.getElementsByTag("img").get(0);
                String src = img.attr("src"); //图片
                String alt = img.attr("alt"); //cos对象
                alt = StringUtils.getFrontPart(alt);
                digest.setCn(alt);
                digest.setPicUrl(src);
                digests.add(digest);
            }
            entity.setHotCos(digests);
            //-------------------------------------------------------------
            List<HotestPostDigest> drawDigests = new ArrayList<HotestPostDigest>();
            Element secondBorad = (board.size() == 0 ? null : board.get(1));
            //work-thumbnail js-img-error work-thumbnail--top work-thumbnail--small
            Elements draws = secondBorad.getElementsByClass("work-thumbnail")
                                        .addClass("js-img-error")
                                        .addClass("work-thumbnail--top")
                                        .addClass("work-thumbnail--small");
            for (Element art : draws) {
                HotestPostDigest digest = new HotestPostDigest();
                Element a = art.getElementsByTag("a").get(0);
                //超链接那里的信息
                String href = a.attr("href");
                String title = a.attr("title");
                digest.setPostUri("http://bcy.net" + href);
                Element img = art.getElementsByTag("img").get(0);
                String src = img.attr("src"); //图片
                String alt = img.attr("alt"); //cos对象
                alt = StringUtils.getFrontPart(alt);
                digest.setPicUrl(src);
                digest.setCn(alt);
                drawDigests.add(digest);
            }
            entity.setHotDraw(drawDigests);
            List<PostDigest> posts = new ArrayList<PostDigest>();
            posts.add(new PostDigest());
            posts.add(new PostDigest());
            posts.add(new PostDigest());
            entity.setPosts(posts);
            return entity;
        }
        return null;
    }

    @Override
    protected void onPostExecute(HomePageEntity posts) {
        super.onPostExecute(posts);
        if (posts == null) {
            mOnFetched.fail("null~", -1);
        } else {
            mOnFetched.succeed(posts);
        }
    }
}
