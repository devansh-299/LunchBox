
package com.tip.lunchbox.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllReviews {

    @SerializedName("reviews")
    @Expose
    private List<Review> reviews = null;

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

}
