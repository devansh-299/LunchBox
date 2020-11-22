package com.tip.lunchbox.model.server.request;

import com.google.gson.annotations.Expose;

public class FavouriteRestaurants {
    @Expose
    private int zomatoResId;

    public String getZomatoResId() {
        return "" + zomatoResId;
    }

    public void setZomatoResId(int zomatoResId) {
        this.zomatoResId = zomatoResId;
    }
}
