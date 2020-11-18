package com.tip.lunchbox.model.server.response;

import com.google.gson.annotations.Expose;

public class CustomResponse {
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }
}
