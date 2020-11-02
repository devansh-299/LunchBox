package com.tip.lunchbox.data.server;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class ApiAuthenticator implements Authenticator {
    @Nullable
    @Override
    public Request authenticate(@Nullable Route route,
                                @NotNull Response response) throws IOException {
        return null;
    }
}
