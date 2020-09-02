package com.tip.lunchbox.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRating {

    @SerializedName("aggregate_rating")
    @Expose
    private String aggregateRating;

    @SerializedName("rating_text")
    @Expose
    private String ratingText;

    @SerializedName("rating_color")
    @Expose
    private String ratingColor;

    @SerializedName("votes")
    @Expose
    private String votes;

    public void setAggregateRating(String aggregateRating) {
        this.aggregateRating = aggregateRating;
    }

    public void setRatingText(String ratingText) {
        this.ratingText = ratingText;
    }

    public void setRatingColor(String ratingColor) {
        this.ratingColor = ratingColor;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }

    public String getAggregateRating() {
        return aggregateRating;
    }

    public String getRatingText() {
        return ratingText;
    }

    public String getRatingColor() {
        return ratingColor;
    }

    public String getVotes() {
        return votes;
    }
}
