package com.tip.lunchbox.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantPhotos {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("thumb_url")
    @Expose
    private String thumbUrl;

    @SerializedName("user")
    @Expose
    private User user;

    @SerializedName("res_id")
    @Expose
    private String resId;

    @SerializedName("caption")
    @Expose
    private String caption;

    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    @SerializedName("friendly_time")
    @Expose
    private String friendlyTime;

    @SerializedName("width")
    @Expose
    private String width;

    @SerializedName("height")
    @Expose
    private String height;

    @SerializedName("comments_count")
    @Expose
    private String commentsCount;

    @SerializedName("likes_count")
    @Expose
    private String likesCount;

    public void setId(String id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setFriendlyTime(String friendlyTime) {
        this.friendlyTime = friendlyTime;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setCommentsCount(String commentsCount) {
        this.commentsCount = commentsCount;
    }

    public void setLikesCount(String likesCount) {
        this.likesCount = likesCount;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public User getUser() {
        return user;
    }

    public String getResId() {
        return resId;
    }

    public String getCaption() {
        return caption;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getFriendlyTime() {
        return friendlyTime;
    }

    public String getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }

    public String getCommentsCount() {
        return commentsCount;
    }

    public String getLikesCount() {
        return likesCount;
    }
}
