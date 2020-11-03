package com.tip.lunchbox.data.server;

import android.text.TextUtils;

import com.tip.lunchbox.LunchBoxApplication;
import com.tip.lunchbox.utilities.Constants;
import com.tip.lunchbox.utilities.SharedPreferencesUtil;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiInterceptor implements Interceptor {

    private static final String AUTHORIZATION = "Authorization";
    private final String authorizationToken =
            SharedPreferencesUtil.getStringPreference(LunchBoxApplication.getContext(),
                    Constants.PREF_AUTH_TOKEN);

    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request.Builder request = chain.request().newBuilder();
        if (!TextUtils.isEmpty(authorizationToken)) {
            request.addHeader(AUTHORIZATION, authorizationToken);
        }
        return chain.proceed(request.build());
    }
}
