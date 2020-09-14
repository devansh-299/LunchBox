
package com.tip.lunchbox.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchResponse {

    @SerializedName("results_found")
    @Expose
    private long resultsFound;
    @SerializedName("results_start")
    @Expose
    private long resultsStart;
    @SerializedName("results_shown")
    @Expose
    private long resultsShown;
    @SerializedName("restaurants")
    @Expose
    private List<RestaurantContainer> restaurantContainers = null;

    public long getResultsFound() {
        return resultsFound;
    }

    public void setResultsFound(long resultsFound) {
        this.resultsFound = resultsFound;
    }

    public long getResultsStart() {
        return resultsStart;
    }

    public void setResultsStart(long resultsStart) {
        this.resultsStart = resultsStart;
    }

    public long getResultsShown() {
        return resultsShown;
    }

    public void setResultsShown(long resultsShown) {
        this.resultsShown = resultsShown;
    }

    public List<RestaurantContainer> getRestaurantContainers() {
        return restaurantContainers;
    }

    public void setRestaurantContainers(List<RestaurantContainer> restaurantContainers) {
        this.restaurantContainers = restaurantContainers;
    }

}
