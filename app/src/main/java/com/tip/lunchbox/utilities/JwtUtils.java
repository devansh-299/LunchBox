package com.tip.lunchbox.utilities;


import android.util.Base64;

import com.google.gson.Gson;
import com.tip.lunchbox.LunchBoxApplication;
import com.tip.lunchbox.model.server.response.AuthTokenClaims;

public class JwtUtils {
    private static String jwtPayload(String jwt) {
        String[] parsed = jwt.split("\\.");
        String encodedBody = parsed[1];
        byte[] decoded = Base64.decode(encodedBody, Base64.DEFAULT);
        return new String(decoded);
    }

    private static AuthTokenClaims jwtPayloadToObject(String payload) {
        Gson gson = new Gson();
        return gson.fromJson(payload, AuthTokenClaims.class);
    }

    public static AuthTokenClaims getAuthTokenClaims() {
        String payload = jwtPayload(SharedPreferencesUtil
                .getStringPreference(LunchBoxApplication.getContext(),
                        Constants.PREF_AUTH_TOKEN));
        return jwtPayloadToObject(payload);
    }

    public static AuthTokenClaims getRefreshTokenClaims() {
        String payload = jwtPayload(SharedPreferencesUtil
                .getStringPreference(LunchBoxApplication.getContext(),
                        Constants.PREF_REFRESH_TOKEN));
        return jwtPayloadToObject(payload);
    }
}
