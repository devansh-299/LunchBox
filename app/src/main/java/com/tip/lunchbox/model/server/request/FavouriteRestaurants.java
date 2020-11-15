package com.tip.lunchbox.model.server.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FavouriteRestaurants {
    @Expose
    @SerializedName("zomato_res_id")
    private String zomatoResId;

    public String getZomatoResId() {
        return zomatoResId;
    }

    public void setZomatoResId(int zomatoResId) {
        this.zomatoResId = Integer.toString(zomatoResId);
    }
}
