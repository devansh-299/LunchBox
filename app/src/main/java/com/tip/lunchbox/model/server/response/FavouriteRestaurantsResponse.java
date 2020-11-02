package com.tip.lunchbox.model.server.response;

import com.google.gson.annotations.Expose;

public class FavouriteRestaurantsResponse {
    @Expose
    private Integer[] result;

    public Integer[] getResult() {
        return result;
    }
}
