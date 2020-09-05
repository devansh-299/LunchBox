package com.tip.lunchbox.data;

import com.tip.lunchbox.model.DailyMenuResponse;
import com.tip.lunchbox.model.Restaurant;
import com.tip.lunchbox.model.Reviews;
import com.tip.lunchbox.model.SearchResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;

public class Repository {

    public Observable<SearchResponse> getSearchResponseObservable(Map<String,String> QueryMap) {
        return ApiManager.getApiService().getSearchResponse(QueryMap);
    }

    public Observable<DailyMenuResponse> getDailyMenuResponseObservable(int restaurantID) {
        return ApiManager.getApiService().getDailyMenuResponse(restaurantID);
    }

    public Observable<List<Reviews>> getReviewsResponseObservable(int restaurantID) {
        return ApiManager.getApiService().getReviewsResponse(restaurantID);
    }

    public Observable<Restaurant> getRestaurantResponseObservable(int restaurantID) {
        return ApiManager.getApiService().getRestaurantResponse(restaurantID);
    }

}
