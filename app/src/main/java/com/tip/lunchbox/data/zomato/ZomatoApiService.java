package com.tip.lunchbox.data.zomato;

import com.tip.lunchbox.model.zomato.CategoryResponse;
import com.tip.lunchbox.model.zomato.CitiesResponse;
import com.tip.lunchbox.model.zomato.CollectionsResponse;
import com.tip.lunchbox.model.zomato.CuisineResponse;
import com.tip.lunchbox.model.zomato.DailyMenuResponse;
import com.tip.lunchbox.model.zomato.EstablishmentResponse;
import com.tip.lunchbox.model.zomato.GeocodeResponse;
import com.tip.lunchbox.model.zomato.Restaurant;
import com.tip.lunchbox.model.zomato.SearchResponse;

import java.util.Map;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ZomatoApiService {

    @GET("search")
    Single<SearchResponse> getSearchResponse(@QueryMap Map<String, String> searchParameters);

    @GET("dailymenu")
    Single<DailyMenuResponse> getDailyMenuResponse(@Query("res_id") int restaurantId);

    @GET("restaurant")
    Single<Restaurant> getRestaurantResponse(@Query("res_id") int restaurantId);

    @GET("collections")
    Single<CollectionsResponse> getCollectionsResponse(@Query("city_id") int cityId);

    @GET("collections")
    Single<CollectionsResponse> getCollectionsResponse(
            @Query("lat") double lat,
            @Query("long") double longitude);

    @GET("cities")
    Single<CitiesResponse> getCitiesResponse(@Query("q") String cityName);

    @GET("cities")
    Single<CitiesResponse> getCitiesResponse(
            @Query("lat") double latitude,
            @Query("lon") double longitude);

    @GET("categories")
    Single<CategoryResponse> getCategoriesResponse();

    @GET("cuisines")
    Single<CuisineResponse> getCuisinesResponse(@Query("city_id") int cityId);

    @GET("cuisines")
    Single<CuisineResponse> getCuisinesResponse(
            @Query("lat") double latitude,
            @Query("lon") double longitude);

    @GET("establishments")
    Single<EstablishmentResponse> getEstablishmentsResponse(@Query("city_id") int cityId);

    @GET("establishments")
    Single<EstablishmentResponse> getEstablishmentsResponse(
            @Query("lat") double latitude,
            @Query("lon") double longitude);

    @GET("geocode")
    Single<GeocodeResponse> getGeocodeResponse(
            @Query("lat") double latitude,
            @Query("lon") double longitude);

}
