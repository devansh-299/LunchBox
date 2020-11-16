package com.tip.lunchbox.data;

import com.tip.lunchbox.data.server.ApiManager;
import com.tip.lunchbox.data.zomato.SearchQuery;
import com.tip.lunchbox.data.zomato.ZomatoApiManager;
import com.tip.lunchbox.model.server.request.Comment;
import com.tip.lunchbox.model.server.request.FavouriteRestaurants;
import com.tip.lunchbox.model.server.request.Login;
import com.tip.lunchbox.model.server.request.SignUp;
import com.tip.lunchbox.model.server.response.CommentsResponse;
import com.tip.lunchbox.model.server.response.CommentsResponseContainer;
import com.tip.lunchbox.model.server.response.CustomResponse;
import com.tip.lunchbox.model.server.response.FavouriteRestaurantsResponse;
import com.tip.lunchbox.model.server.response.RefreshResponse;
import com.tip.lunchbox.model.server.response.Tokens;
import com.tip.lunchbox.model.zomato.CategoryResponse;
import com.tip.lunchbox.model.zomato.CitiesResponse;
import com.tip.lunchbox.model.zomato.CollectionsResponse;
import com.tip.lunchbox.model.zomato.CuisineResponse;
import com.tip.lunchbox.model.zomato.DailyMenuResponse;
import com.tip.lunchbox.model.zomato.EstablishmentResponse;
import com.tip.lunchbox.model.zomato.GeocodeResponse;
import com.tip.lunchbox.model.zomato.Restaurant;
import com.tip.lunchbox.model.zomato.SearchResponse;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

/**
 * Repository acts as the common point of interaction between 'View Model' layer and both set
 * of back-ends (Zomato and our own back-end) that this application is consuming.
 */
public class Repository {

    // For Zomato's Services

    public Single<SearchResponse> getSearchResponseObservable(SearchQuery searchQuery) {
        return ZomatoApiManager.getZomatoApiService().getSearchResponse(searchQuery.getParams());
    }

    public Single<DailyMenuResponse> getDailyMenuResponseObservable(int restaurantId) {
        return ZomatoApiManager.getZomatoApiService().getDailyMenuResponse(restaurantId);
    }

    public Single<Restaurant> getRestaurantResponseObservable(int restaurantId) {
        return ZomatoApiManager.getZomatoApiService().getRestaurantResponse(restaurantId);
    }

    public Single<CollectionsResponse> getCollectionsResponseObservable(int cityId) {
        return ZomatoApiManager.getZomatoApiService().getCollectionsResponse(cityId);
    }

    public Single<CollectionsResponse> getCollectionsResponseObservable(double lat, double lon) {
        return ZomatoApiManager.getZomatoApiService().getCollectionsResponse(lat, lon);
    }

    public Single<CategoryResponse> getCategoryResponseObservable() {
        return ZomatoApiManager.getZomatoApiService().getCategoriesResponse();
    }

    public Single<CuisineResponse> getCuisineResponseObservable(int cityId) {
        return ZomatoApiManager.getZomatoApiService().getCuisinesResponse(cityId);
    }

    public Single<CuisineResponse> getCuisineResponseObservable(double lat, double lon) {
        return ZomatoApiManager.getZomatoApiService().getCuisinesResponse(lat, lon);
    }

    public Single<EstablishmentResponse> getEstablishmentsResponseObservable(int cityId) {
        return ZomatoApiManager.getZomatoApiService().getEstablishmentsResponse(cityId);
    }

    public Single<EstablishmentResponse> getEstablishmentsResponseObservable(
            double lat, double lon) {
        return ZomatoApiManager.getZomatoApiService().getEstablishmentsResponse(lat, lon);
    }

    public Single<CitiesResponse> getCitiesResponseObservable(String city) {
        return ZomatoApiManager.getZomatoApiService().getCitiesResponse(city);
    }

    public Single<CitiesResponse> getCitiesResponseObservable(double lat, double lon) {
        return ZomatoApiManager.getZomatoApiService().getCitiesResponse(lat, lon);
    }

    public Single<GeocodeResponse> getGeocodeResponseObservable(double lat, double lon) {
        return ZomatoApiManager.getZomatoApiService().getGeocodeResponse(lat, lon);
    }

    // For our server's services

    public Single<Tokens> loginUser(Login login) {
        return ApiManager.getApiService().loginUser(login);
    }

    public Single<Tokens> signUpUser(SignUp signUp) {
        return ApiManager.getApiService().signUpUser(signUp);
    }

    public Single<CommentsResponseContainer> getComments(String resId) {
        return ApiManager.getApiService().getComment(resId);
    }

    public Single<FavouriteRestaurantsResponse> getFavouriteRestaurants(String username) {
        return ApiManager.getApiService().getUserFav(username);
    }

    public Single<CustomResponse> postComment(Comment comment) {
        return ApiManager.getApiService().postComment(comment);
    }

    public Single<CustomResponse> postUserFav(FavouriteRestaurants favouriteRestaurants) {
        return ApiManager.getApiService().postUserFav(favouriteRestaurants);
    }

    public Single<RefreshResponse> refreshResponseSingle() {
        return ApiManager.getApiService().refresh();
    }

    public Single<CommentsResponseContainer> getUserComments(String username){
        return ApiManager.getApiService().getUserComments(username);
    }
}


