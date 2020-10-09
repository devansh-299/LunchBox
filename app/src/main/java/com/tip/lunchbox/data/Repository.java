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

    public Single<DailyMenuResponse> getDailyMenuResponseObservable(int restaurantId) {
        return ApiManager.getApiService().getDailyMenuResponse(restaurantId);
    }

    public Single<ReviewResponse> getReviewsResponseObservable(int restaurantId) {
        return ApiManager.getApiService().getReviewsResponse(restaurantId);
    }

    public Single<RestaurantContainer> getRestaurantResponseObservable(int restaurantId) {
        return ApiManager.getApiService().getRestaurantResponse(restaurantId);
    }

    public Single<CollectionsResponse> getCollectionsResponseObservable(int cityId) {
        return ApiManager.getApiService().getCollectionsResponse(cityId);
    }

    public Single<CollectionsResponse> getCollectionsResponseObservable(double lat, double lon) {
        return ApiManager.getApiService().getCollectionsResponse(lat, lon);
    }

    public Single<CategoryResponse> getCategoryResponseObservable() {
        return ApiManager.getApiService().getCategoriesResponse();
    }

    public Single<CuisineResponse> getCuisineResponseObservable(int cityId) {
        return ApiManager.getApiService().getCuisinesResponse(cityId);
    }

    public Single<CuisineResponse> getCuisineResponseObservable(double lat, double lon) {
        return ApiManager.getApiService().getCuisinesResponse(lat, lon);
    }

    public Single<EstablishmentResponse> getEstablishmentsResponseObservable(int cityId) {
        return ApiManager.getApiService().getEstablishmentsResponse(cityId);
    }

    public Single<EstablishmentResponse> getEstablishmentsResponseObservable(
            double lat, double lon) {
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


