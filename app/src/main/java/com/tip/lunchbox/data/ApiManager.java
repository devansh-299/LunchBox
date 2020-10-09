package com.tip.lunchbox.data;

import com.tip.lunchbox.data.service.ApiService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    private static String BASEURL = "https://developers.zomato.com/api/v2.1/";

    private static Retrofit retrofit;

    private static ApiService apiService;

    private static Retrofit getRetrofitClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASEURL)
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
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(logging)
                .build();
    }

    public static ApiService getApiService() {
        if (apiService == null) {
            apiService = getRetrofitClient().create(ApiService.class);
        }
        return apiService;
    }
}
