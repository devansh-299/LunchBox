package com.tip.lunchbox.data.service;

import com.tip.lunchbox.model.DailyMenuResponse;
import com.tip.lunchbox.model.Restaurant;
import com.tip.lunchbox.model.Reviews;
import com.tip.lunchbox.model.SearchResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface APIService {

    @GET("search")
    Observable<SearchResponse> getSearchResponse(@QueryMap Map<String, String> searchParameters);

    @GET("dailymenu")
    Observable<DailyMenuResponse> getDailyMenuResponse(@Query("res_id") int restaurantID);

    @GET("reviews")
    Observable<List<Reviews>> getReviewsResponse(@Query("res_id") int restaurantID);

    @GET("restaurant")
    Observable<Restaurant> getRestaurantResponse(@Query("res_id") int restaurantID);

}
