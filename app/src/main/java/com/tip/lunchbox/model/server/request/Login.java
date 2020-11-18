package com.tip.lunchbox.model.server.request;

import com.google.gson.annotations.Expose;

public class Login {
    @Expose
    private String username;
    @Expose
    private String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
