package com.tip.lunchbox.model.zomato;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CuisineResponse {

    @SerializedName("cuisines")
    @Expose
    private List<CuisineContainer> cuisines = null;

    public List<CuisineContainer> getCuisines() {
        return cuisines;
    }

    public void setCuisines(List<CuisineContainer> cuisines) {
        this.cuisines = cuisines;
    }

}
