package com.tip.lunchbox.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("zomato_handle")
    @Expose
    private String zomatoHandle;

    @SerializedName("foodie_level")
    @Expose
    private String foodieLevel;

    @SerializedName("foodie_level_num")
    @Expose
    private String foodieLevelNum;

    @SerializedName("foodie_color")
    @Expose
    private String foodieColor;

    @SerializedName("profile_url")
    @Expose
    private String profileUrl;

    @SerializedName("profile_deeplink")
    @Expose
    private String profileDeepLink;

    @SerializedName("profile_image")
    @Expose
    private String profileImage;

    public void setName(String name) {
        this.name = name;
    }

    public void setZomatoHandle(String zomatoHandle) {
        this.zomatoHandle = zomatoHandle;
    }

    public void setFoodieLevel(String foodieLevel) {
        this.foodieLevel = foodieLevel;
    }

    public void setFoodieLevelNum(String foodieLevelNum) {
        this.foodieLevelNum = foodieLevelNum;
    }

    public void setFoodieColor(String foodieColor) {
        this.foodieColor = foodieColor;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public void setProfileDeepLink(String profileDeepLink) {
        this.profileDeepLink = profileDeepLink;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getName() {
        return name;
    }

    public String getZomatoHandle() {
        return zomatoHandle;
    }

    public String getFoodieLevel() {
        return foodieLevel;
    }

    public String getFoodieLevelNum() {
        return foodieLevelNum;
    }

    public String getFoodieColor() {
        return foodieColor;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public String getProfileDeepLink() {
        return profileDeepLink;
    }

    public String getProfileImage() {
        return profileImage;
    }
}
