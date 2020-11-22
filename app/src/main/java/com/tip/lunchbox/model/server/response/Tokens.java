package com.tip.lunchbox.model.server.response;

import com.google.gson.annotations.Expose;

//Response for both signup and login endpoints
public class Tokens {
    @Expose
    private String authToken;
    @Expose
    private String refreshToken;

    public String getAuthToken() {
        return authToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
