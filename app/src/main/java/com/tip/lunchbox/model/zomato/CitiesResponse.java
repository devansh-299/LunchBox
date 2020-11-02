package com.tip.lunchbox.model.zomato;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CitiesResponse {

    @SerializedName("location_suggestions")
    @Expose
    private List<LocationSuggestion> locationSuggestions = null;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("has_more")
    @Expose
    private int hasMore;
    @SerializedName("has_total")
    @Expose
    private int hasTotal;
    @SerializedName("user_has_addresses")
    @Expose
    private boolean userHasAddresses;

    public List<LocationSuggestion> getLocationSuggestions() {
        return locationSuggestions;
    }

    public void setLocationSuggestions(List<LocationSuggestion> locationSuggestions) {
        this.locationSuggestions = locationSuggestions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getHasMore() {
        return hasMore;
    }

    public void setHasMore(int hasMore) {
        this.hasMore = hasMore;
    }

    public int getHasTotal() {
        return hasTotal;
    }

    public void setHasTotal(int hasTotal) {
        this.hasTotal = hasTotal;
    }

    public boolean isUserHasAddresses() {
        return userHasAddresses;
    }

    public void setUserHasAddresses(boolean userHasAddresses) {
        this.userHasAddresses = userHasAddresses;
    }

}
