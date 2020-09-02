package com.tip.lunchbox.data;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder request = chain.request().newBuilder();
        //TODO add APIKEY
        request.addHeader("user-key","insertAPIKEY");
        return chain.proceed(request.build());

    }
}
