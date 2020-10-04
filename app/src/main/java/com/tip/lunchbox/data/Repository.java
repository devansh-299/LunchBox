package com.tip.lunchbox.data;

import com.tip.lunchbox.model.CategoryResponse;
import com.tip.lunchbox.model.CitiesResponse;
import com.tip.lunchbox.model.CollectionsResponse;
import com.tip.lunchbox.model.CuisineResponse;
import com.tip.lunchbox.model.DailyMenuResponse;
import com.tip.lunchbox.model.EstablishmentResponse;
import com.tip.lunchbox.model.GeocodeResponse;
import com.tip.lunchbox.model.RestaurantContainer;
import com.tip.lunchbox.model.ReviewResponse;
import com.tip.lunchbox.model.SearchResponse;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public class Repository {


    public Single<SearchResponse> getSearchResponseObservable(SearchQuery searchQuery) {
        return ApiManager.getApiService().getSearchResponse(searchQuery.getParams());
    }

    public Single<DailyMenuResponse> getDailyMenuResponseObservable(int restaurantID) {
        return ApiManager.getApiService().getDailyMenuResponse(restaurantID);
    }

    public Single<ReviewResponse> getReviewsResponseObservable(int restaurantID) {
        return ApiManager.getApiService().getReviewsResponse(restaurantID);
    }

    public Single<RestaurantContainer> getRestaurantResponseObservable(int restaurantID) {
        return ApiManager.getApiService().getRestaurantResponse(restaurantID);
    }

    public Single<CollectionsResponse> getCollectionsResponseObservable(int cityID) {
        return ApiManager.getApiService().getCollectionsResponse(cityID);
    }

    public Single<CollectionsResponse> getCollectionsResponseObservable(double lat, double lon) {
        return ApiManager.getApiService().getCollectionsResponse(lat, lon);
    }

    public Single<CategoryResponse> getCategoryResponseObservable() {
        return ApiManager.getApiService().getCategoriesResponse();
    }

    public Single<CuisineResponse> getCuisineResponseObservable(int cityID) {
        return ApiManager.getApiService().getCuisinesResponse(cityID);
    }

    public Single<CuisineResponse> getCuisineResponseObservable(double lat, double lon) {
        return ApiManager.getApiService().getCuisinesResponse(lat, lon);
    }

    public Single<EstablishmentResponse> getEstablishmentsResponseObservable(int cityID) {
        return ApiManager.getApiService().getEstablishmentsResponse(cityID);
    }

    public Single<EstablishmentResponse> getEstablishmentsResponseObservable(double lat, double lon) {
        return ApiManager.getApiService().getEstablishmentsResponse(lat, lon);
    }

    public Single<CitiesResponse> getCitiesResponseObservable(String city) {
        return ApiManager.getApiService().getCitiesResponse(city);
    }

    public Single<CitiesResponse> getCitiesResponseObservable(double lat, double lon) {
        return ApiManager.getApiService().getCitiesResponse(lat, lon);
    }

    public Single<GeocodeResponse> getGeocodeResponseObservable(double lat, double lon) {
        return ApiManager.getApiService().getGeocodeResponse(lat, lon);
    }
}


