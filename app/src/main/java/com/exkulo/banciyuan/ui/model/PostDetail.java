package com.exkulo.banciyuan.ui.model;

import com.exkulo.banciyuan.ui.utils.WrappedString;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Collection;
import java.util.List;

/**
 * Created by exkulo on 10/2/2015.
 */
@DatabaseTable(tableName = "post_detail")
public class PostDetail extends BaseModel {
    @DatabaseField(columnName = "txt") private String txt;
    @ForeignCollectionField private Collection<WrappedString> imgUrls;
    /**
     * 原作
     */
    @DatabaseField(columnName = "orignalArt") private String orignalArt;
    /**
     * 角色
     */
    @DatabaseField(columnName = "character") private String character;
    @DatabaseField(columnName = "cn") private String cn;
    //@ForeignCollectionField(eager = false) private List<Comment> comments;
    /**
     * 帖子所在的网址
     */
    @DatabaseField(id = true, columnName = "postUrl") private String postUrl;

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    //public List<Comment> getComments() {
    //    return comments;
    //}
    //
    //public void setComments(List<Comment> comments) {
    //    this.comments = comments;
    //}

    public Collection<WrappedString> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(Collection<WrappedString> imgUrls) {
        this.imgUrls = imgUrls;
    }

    public String getOrignalArt() {
        return orignalArt;
    }

    public void setOrignalArt(String orignalArt) {
        this.orignalArt = orignalArt;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }

    @Override
    public String toString() {
        return "PostDetail{" +
            "character='" + character + '\'' +
            ", txt='" + txt + '\'' +
            ", imgUrls=" + imgUrls +
            ", orignalArt='" + orignalArt + '\'' +
            ", cn='" + cn + '\'' +
            '}';
    }
}
