package com.tip.lunchbox.model.server.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentsResponse {
    @SerializedName("_id")
    @Expose
    private String id;
    @Expose
    private String title;
    @Expose
    private String comment;
    @Expose
    private String zomatoResId;
    @SerializedName("user_name")
    private String author;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getComment() {
        return comment;
    }

    public String getZomatoResId() {
        return zomatoResId;
    }

    public String getAuthor() {
        return author;
    }
}
