package com.exkulo.banciyuan.ui.model;

/**
 * Created by exkulo on 9/15/2015.
 * 首页帖子
 */
public class PostDigest extends BaseModel{

    private String imgUri;
    private String animation;
    private String cn;
    private String time;

    public String getAnimation() {
        return animation;
    }

    public void setAnimation(String animation) {
        this.animation = animation;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
