package com.exkulo.banciyuan.ui.model;

/**
 * Created by exkulo on 9/15/2015.
 */
public class HotestPostDigest extends BaseModel{

    private String picUrl;
    private String cn;
    private int browseCount;
    private int rank;
    private String postUri;

    public int getBrowseCount() {
        return browseCount;
    }

    public void setBrowseCount(int browseCount) {
        this.browseCount = browseCount;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getPostUri() {
        return postUri;
    }

    public void setPostUri(String postUri) {
        this.postUri = postUri;
    }

    @Override
    public String toString() {
        return "HotestPostDigest{" +
            "browseCount=" + browseCount +
            ", picUrl='" + picUrl + '\'' +
            ", cn='" + cn + '\'' +
            ", rank=" + rank +
            ", postUri='" + postUri + '\'' +
            '}';
    }
}
