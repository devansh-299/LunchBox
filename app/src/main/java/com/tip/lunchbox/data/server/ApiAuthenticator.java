package com.tip.lunchbox.data.server;

import android.content.Intent;

import com.tip.lunchbox.LunchBoxApplication;
import com.tip.lunchbox.utilities.Constants;
import com.tip.lunchbox.utilities.SharedPreferencesUtil;
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
        if (!response.request().url().encodedPath().equals("/login")) {
            if (response.code() == 401) {
                //TODO setup should be changed to login activity
                Intent intent = new Intent(LunchBoxApplication.getContext(), SetupActivity.class);
                intent.putExtra(Constants.LOGOUT_MESSAGE, "Session expired, please log in again.");
                SharedPreferencesUtil
                        .setStringPreference(LunchBoxApplication.getContext(),
                                Constants.PREF_AUTH_TOKEN, "");
                SharedPreferencesUtil
                        .setStringPreference(LunchBoxApplication.getContext(),
                                Constants.PREF_REFRESH_TOKEN, "");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                LunchBoxApplication.getInstance().startActivity(intent);
                return null;
            }
        }

        return response.request();
    }
}
