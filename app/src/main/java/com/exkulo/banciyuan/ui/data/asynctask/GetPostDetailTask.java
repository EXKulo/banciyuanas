package com.exkulo.banciyuan.ui.data.asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import com.exkulo.banciyuan.ui.data.OnFetched;
import com.exkulo.banciyuan.ui.data.dao.DataBaseHelper;
import com.exkulo.banciyuan.ui.model.PostDetail;
import com.exkulo.banciyuan.ui.utils.JsoupUtil;
import com.exkulo.banciyuan.ui.utils.L;
import com.exkulo.banciyuan.ui.utils.WrappedString;
import com.j256.ormlite.dao.ForeignCollection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by exkulo on 10/2/2015.
 */
public class GetPostDetailTask extends AsyncTask<String, Integer, PostDetail> {

    private OnFetched mOnFetched;

    public GetPostDetailTask(OnFetched onFetched) {
        if (onFetched == null) throw new IllegalStateException("onFetched can not be null!");
        mOnFetched = onFetched;
    }

    @Override
    protected PostDetail doInBackground(String... params) {
        Document document = JsoupUtil.getDocument(params[0]);
        if (document != null) {
            PostDetail postDetail = new PostDetail();
            //detail_std detail_clickable 拿到图片等等
            Elements picElements =
                document.getElementsByAttributeValue("class", "detail_std detail_clickable");
            if (picElements.isEmpty()) return null;
            Collection<WrappedString> picUrls = new ArrayList<WrappedString>();
            for (Element element : picElements) {
                picUrls.add(new WrappedString(element.attr("src")));
            }
            postDetail.setImgUrls(picUrls);
            //出自作品
            Elements orignal =
                document.getElementsByAttributeValue("class", "_icon-text l-left mt1");
            if (!orignal.isEmpty()) {
                postDetail.setOrignalArt(orignal.get(0).text());
            } else {
                orignal = document.getElementsByAttributeValue("class", "i-original-secondary");
                postDetail.setOrignalArt(orignal.get(0).text());
            }

            //正文部分
            Elements txtOrignal = document.getElementsByAttributeValue("class",
                "post__content js-content-img-wrap js-fullimg js-maincontent mb20");
            if(!txtOrignal.isEmpty()) {
                Elements txt = Jsoup.parse(txtOrignal.get(0).toString().replace("<br>","$$$")).getAllElements();
                String txtStr = txt.get(0).text().replace("$$$", "\n");
                txtStr = txtStr.substring(0, txtStr.length() - picUrls.size() * 2 + 1);
                postDetail.setTxt(txtStr);
            }
            //character
            Elements characterI = document.getElementsByClass("i-role-white");
            Element character = characterI.parents().get(0);
            postDetail.setCharacter(character.text().replace("<br>", "\n"));
            //cn
            Element charLevel = document.getElementsByClass("post__role-headline").get(0);
            Element span = charLevel.parent();
            if(!span.getElementsByTag("h3").isEmpty()) {
                postDetail.setCn(
                    span.getElementsByTag("h3").get(0).getElementsByTag("a").get(0).text());
            }
            postDetail.setPostUrl(params[0]);
            return postDetail;
        }
        return null;
    }

    @Override
    protected void onPostExecute(PostDetail postDetail) {
        super.onPostExecute(postDetail);
        if (postDetail == null) {
            mOnFetched.fail("null~", -1);
        } else {
            try {
                DataBaseHelper.getHelper().getUserDao().create(postDetail);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            mOnFetched.succeed(postDetail);
        }
    }
}
