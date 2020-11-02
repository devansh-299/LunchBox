package com.tip.lunchbox.data.zomato;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ZomatoApiManager {

    private static final String ZOMATO_BASE_URL = "https://developers.zomato.com/api/v2.1/";
    private static final String BASE_URL = "https://lunchbox-backend.herokuapp.com/";

    private static Retrofit retrofit;
    private static ZomatoApiService zomatoApiService;

    private static Retrofit getRetrofitClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(ZOMATO_BASE_URL)
                    .client(getOkHttpClient())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

    private static OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient()
                .newBuilder()
                .addInterceptor(new ZomatoApiInterceptor())
                .addInterceptor(logging)
                .build();
    }

    public static ZomatoApiService getZomatoApiService() {
        if (zomatoApiService == null) {
            zomatoApiService = getRetrofitClient().create(ZomatoApiService.class);
        }
        return zomatoApiService;
    }
}
