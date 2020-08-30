package com.tip.lunchbox.model;

import com.google.gson.annotations.SerializedName;

public class Reviews {
    @SerializedName("rating") 
        private String rating;

    @SerializedName("review_text")
        private String reviewText;

    @SerializedName("id")
        private String id;

    @SerializedName("rating_color")
        private String ratingColor;

    @SerializedName("review_time_friendly")
        private String reviewTimeFriendly;

    @SerializedName("rating_text")
        private String ratingText;

    @SerializedName("timestamp")
        private String timestamp;

    @SerializedName("likes")
        private String likes;

    @SerializedName("user")
        private User user;

    @SerializedName("comments_count")
        private String commentsCount;

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRatingColor(String ratingColor) {
        this.ratingColor = ratingColor;
    }

    public void setReviewTimeFriendly(String reviewTimeFriendly) {
        this.reviewTimeFriendly = reviewTimeFriendly;
    }

    public void setRatingText(String ratingText) {
        this.ratingText = ratingText;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCommentsCount(String commentsCount) {
        this.commentsCount = commentsCount;
    }

    public String getRating() {
        return rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public String getId() {
        return id;
    }

    public String getRatingColor() {
        return ratingColor;
    }

    public String getReviewTimeFriendly() {
        return reviewTimeFriendly;
    }

    public String getRatingText() {
        return ratingText;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getLikes() {
        return likes;
    }

    public User getUser() {
        return user;
    }

    public String getCommentsCount() {
        return commentsCount;
    }
}
