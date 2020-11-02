package com.tip.lunchbox.model.zomato;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RatingObj {

    @SerializedName("title")
    @Expose
    private Title title;
    @SerializedName("bg_color")
    @Expose
    private BgColor bgColor;

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public BgColor getBgColor() {
        return bgColor;
    }

    public void setBgColor(BgColor bgColor) {
        this.bgColor = bgColor;
    }

}
