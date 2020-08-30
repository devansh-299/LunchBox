package com.tip.lunchbox.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("name")
        private String name;

    @SerializedName("zomato_handle")
        private String zomatoHandle;

    @SerializedName("foodie_level")
        private String foodieLevel;

    @SerializedName("foodie_level_num")
        private String foodieLevelNum;

    @SerializedName("foodie_color")
        private String foodieColor;

    @SerializedName("profile_url")
        private String profileUrl;

    @SerializedName("profile_deeplink")
        private String profileDeeplink;

    @SerializedName("profile_image")
        private String profileImage;

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

    public String getProfileDeeplink() {
        return profileDeeplink;
    }

    public String getProfileImage() {
        return profileImage;
    }
}
