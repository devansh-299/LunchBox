package com.tip.lunchbox.data.service;

import com.tip.lunchbox.model.CollectionsResponse;
import com.tip.lunchbox.model.DailyMenuResponse;
import com.tip.lunchbox.model.RestaurantContainer;
import com.tip.lunchbox.model.ReviewResponse;
import com.tip.lunchbox.model.SearchResponse;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface APIService {

    @GET("search")
    Observable<SearchResponse> getSearchResponse(@QueryMap Map<String, String> searchParameters);

    @GET("dailymenu")
    Observable<DailyMenuResponse> getDailyMenuResponse(@Query("res_id") int restaurantID);
    //TODO fix this
    @GET("reviews")
    Observable<ReviewResponse> getReviewsResponse(@Query("res_id") int restaurantID);

    @GET("restaurant")
    Observable<RestaurantContainer> getRestaurantResponse(@Query("res_id") int restaurantID);

    @GET("collections")
    Single<CollectionsResponse> getCollectionsResponse(@Query("city_id") int cityID);

    @GET("collections")
    Single<CollectionsResponse> getCollectionsResponse(@Query("lat") int lat , @Query("long") int longitude);

}
