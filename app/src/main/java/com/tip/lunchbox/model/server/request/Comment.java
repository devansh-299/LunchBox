package com.tip.lunchbox.model.server.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment {
    @Expose
    private String comment;
    @Expose
    private int rating;
    @Expose
    private int zomatoResId;

    public Comment(String comment, int rating, String zomatoResId) {
        this.comment = comment;
        this.rating = rating;
        this.zomatoResId = Integer.parseInt(zomatoResId);
    }

    public String getComment() {
        return comment;
    }


    public int getRating() {
        return rating;
    }


    public String getZomatoResId() {
        return "" + zomatoResId;
    }

}
