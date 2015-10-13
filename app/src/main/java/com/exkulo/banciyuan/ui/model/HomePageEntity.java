package com.exkulo.banciyuan.ui.model;

import java.util.List;

/**
 * Created by exkulo on 9/15/2015.
 * 整个首页看作一个实体类
 */
public class HomePageEntity extends BaseModel {

    private List<HotestPostDigest> hotCos;
    private List<HotestPostDigest> hotDraw;
    private List<PostDigest> posts;

    public List<HotestPostDigest> getHotCos() {
        return hotCos;
    }

    public void setHotCos(List<HotestPostDigest> hotCos) {
        this.hotCos = hotCos;
    }

    public List<HotestPostDigest> getHotDraw() {
        return hotDraw;
    }

    public void setHotDraw(List<HotestPostDigest> hotDraw) {
        this.hotDraw = hotDraw;
    }

    public List<PostDigest> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDigest> posts) {
        this.posts = posts;
    }
}
