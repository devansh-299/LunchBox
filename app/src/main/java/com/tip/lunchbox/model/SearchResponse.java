package com.tip.lunchbox.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse {

    @SerializedName("results_found")
    public String resultsFound;

    @SerializedName("results_start")
    public String resultsStart;

    @SerializedName("results_shown")
    public String resultsShown;

    @SerializedName("restaurants")
    public List<Restaurant> restaurants = null;

    public String getResultsFound() {
        return resultsFound;
    }

    public void setResultsFound(String resultsFound) {
        this.resultsFound = resultsFound;
    }

    public String getResultsStart() {
        return resultsStart;
    }

    public void setResultsStart(String resultsStart) {
        this.resultsStart = resultsStart;
    }

    public String getResultsShown() {
        return resultsShown;
    }

    public void setResultsShown(String resultsShown) {
        this.resultsShown = resultsShown;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}