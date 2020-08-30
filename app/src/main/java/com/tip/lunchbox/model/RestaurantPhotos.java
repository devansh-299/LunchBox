package com.tip.lunchbox.model;

import com.google.gson.annotations.SerializedName;

public class RestaurantPhotos {
    @SerializedName("id")
        private String id;

    @SerializedName("url")
        private String url;

    @SerializedName("thumb_url")
        private String thumbUrl;

    @SerializedName("user")
        private User user;

    @SerializedName("res_id")
        private String resId;

    @SerializedName("caption")
        private String caption;

    @SerializedName("timestamp")
        private String timestamp;

    @SerializedName("friendly_time")
        private String friendlyTime;

    @SerializedName("width")
        private String width;

    @SerializedName("height")
        private String height;

    @SerializedName("comments_count")
        private String commentsCount;

    @SerializedName("likes_count")
        private String likesCount;

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
