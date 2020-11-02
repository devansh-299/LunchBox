package com.tip.lunchbox.data.zomato;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

// To intercept request calls to Zomato's back-end
public class ZomatoApiInterceptor implements Interceptor {

    private static final String USER_KEY = "user-key";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder request = chain.request().newBuilder();
        // TODO add API_KEY
        request.addHeader(USER_KEY,"ZOMATO_API_KEY");
        return chain.proceed(request.build());
    }
}
