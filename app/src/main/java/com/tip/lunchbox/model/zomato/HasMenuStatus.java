package com.tip.lunchbox.model.zomato;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HasMenuStatus {

    @SerializedName("delivery")
    @Expose
    private long delivery;
    @SerializedName("takeaway")
    @Expose
    private long takeaway;

    public long getDelivery() {
        return delivery;
    }

    public void setDelivery(long delivery) {
        this.delivery = delivery;
    }

    public long getTakeaway() {
        return takeaway;
    }

    public void setTakeaway(long takeaway) {
        this.takeaway = takeaway;
    }

}
