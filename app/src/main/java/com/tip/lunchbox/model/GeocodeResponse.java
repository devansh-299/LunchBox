package com.tip.lunchbox.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GeocodeResponse {

    @SerializedName("location")
    @Expose
    private Locality locality;
    @SerializedName("popularity")
    @Expose
    private Popularity popularity;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("nearby_restaurants")
    @Expose
    private List<RestaurantContainer> nearbyRestaurantContainers = null;

    public Locality getLocality() {
        return locality;
    }

    public void setLocality(Locality locality) {
        this.locality = locality;
    }

    public Popularity getPopularity() {
        return popularity;
    }

    public void setPopularity(Popularity popularity) {
        this.popularity = popularity;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<RestaurantContainer> getNearbyRestaurantContainers() {
        return nearbyRestaurantContainers;
    }

    public void setNearbyRestaurantContainers(
            List<RestaurantContainer> nearbyRestaurantContainers) {
        this.nearbyRestaurantContainers = nearbyRestaurantContainers;
    }

}