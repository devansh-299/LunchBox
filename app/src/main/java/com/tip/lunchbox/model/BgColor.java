
package com.tip.lunchbox.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BgColor {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("tint")
    @Expose
    private String tint;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTint() {
        return tint;
    }

    public void setTint(String tint) {
        this.tint = tint;
    }

}
