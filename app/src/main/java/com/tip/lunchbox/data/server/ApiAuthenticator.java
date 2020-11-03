package com.tip.lunchbox.data.server;

import android.content.Intent;

import com.tip.lunchbox.LunchBoxApplication;
import com.tip.lunchbox.view.activity.SetupActivity;

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
        // Verification can also be done on front if token has expired or not.
        if (response.code() == 401 && response.request().url().encodedPath().equals("/refresh")) {
            //TODO setup should be changed to login activity
            Intent intent = new Intent(LunchBoxApplication.getContext(), SetupActivity.class);
            intent.putExtra("message","Token Expired Please Sign in again");
            return null;
        }

        return response.request();
    }
}
