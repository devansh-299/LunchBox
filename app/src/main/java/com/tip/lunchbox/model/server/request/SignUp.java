package com.tip.lunchbox.model.server.request;

import com.google.gson.annotations.Expose;

/**
 * This class represents a request made to the SignUp endpoint of the api
 * Username represents username of the user
 * password represents password of the user
 * phone represents phone number of the user.
 **/
public class SignUp {
    @Expose
    private String username;
    @Expose
    private String password;
    @Expose
    private long phone;

    public SignUp(String username, String password, long phone) {
        this.username = username;
        this.password = password;
        this.phone = phone;
    }
}
