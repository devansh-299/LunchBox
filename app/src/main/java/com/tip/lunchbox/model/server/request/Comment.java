package com.tip.lunchbox.model.server.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment {
    @Expose
    private String comment;
    @Expose
    private String title;
    @Expose
    private String zomatoResId;

    public Comment(String comment, String title, String zomatoResId) {
        this.comment = comment;
        this.title = title;
        this.zomatoResId = zomatoResId;
    }

    public String getComment() {
        return comment;
    }


    public String getTitle() {
        return title;
    }


    public String getZomatoResId() {
        return zomatoResId;
    }

}
