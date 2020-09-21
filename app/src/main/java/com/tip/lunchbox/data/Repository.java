package com.tip.lunchbox.data;

import com.tip.lunchbox.model.CollectionsResponse;
import com.tip.lunchbox.model.DailyMenuResponse;
import com.tip.lunchbox.model.RestaurantContainer;
import com.tip.lunchbox.model.ReviewResponse;
import com.tip.lunchbox.model.SearchResponse;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class Repository {



    public Observable<SearchResponse> getSearchResponseObservable(Map<String,String> QueryMap) {
       return ApiManager.getApiService().getSearchResponse(QueryMap);
    }

    public Observable<DailyMenuResponse> getDailyMenuResponseObservable(int restaurantID) {
        return ApiManager.getApiService().getDailyMenuResponse(restaurantID);
    }

    public Observable<ReviewResponse> getReviewsResponseObservable(int restaurantID) {
        return ApiManager.getApiService().getReviewsResponse(restaurantID);
    }

    public Observable<RestaurantContainer> getRestaurantResponseObservable(int restaurantID) {
        return ApiManager.getApiService().getRestaurantResponse(restaurantID);
    }

    public Single<CollectionsResponse> getCollectionsResponseObservable(int cityID){
        return ApiManager.getApiService().getCollectionsResponse(cityID);
    }
}
