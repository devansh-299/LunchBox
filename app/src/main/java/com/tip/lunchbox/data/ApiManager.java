package com.tip.lunchbox.data;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    private static String BASEURL = "";

    private static Retrofit retrofit;

    public static Retrofit getRetrofitClient() {
        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .client(getOkHttpClient())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit;
    }

    private static OkHttpClient getOkHttpClient() {
        return new OkHttpClient()
                .newBuilder()
                .addInterceptor(new HeaderInterceptor())
                .build();
    }

}
