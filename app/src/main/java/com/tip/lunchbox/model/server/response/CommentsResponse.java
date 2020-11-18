package com.tip.lunchbox.model.server.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentsResponse {
    @SerializedName("_id")
    @Expose
    private String id;
    @Expose
    private Integer rating;
    @Expose
    private String comment;
    @Expose
    private int zomatoResId;
    @SerializedName("user_name")
    private String author;

    public String getId() {
        return id;
    }

    public Integer getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public int getZomatoResId() {
        return zomatoResId;
    }

    public String getAuthor() {
        return author;
    }
}
