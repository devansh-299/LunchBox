package com.tip.lunchbox.model.server.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment {
    @Expose
    private String comment;
    @Expose
    private String title;
    @Expose
    @SerializedName("zomato_res_id")
    private String zomatoResId;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getZomatoResId() {
        return zomatoResId;
    }

    public void setZomatoResId(String zomatoResId) {
        this.zomatoResId = zomatoResId;
    }
}
