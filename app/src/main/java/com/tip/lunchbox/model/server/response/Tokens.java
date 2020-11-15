package com.tip.lunchbox.model.server.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//Response for both signup and login endpoints
public class Tokens {
    @Expose
    @SerializedName("auth_token")
    private String authToken;
    @Expose
    @SerializedName("refresh_token")
    private String refreshToken;

    public String getAuthToken() {
        return authToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
