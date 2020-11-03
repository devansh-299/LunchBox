package com.tip.lunchbox.data.server;

import android.database.Observable;

import com.tip.lunchbox.model.server.request.Comment;
import com.tip.lunchbox.model.server.request.FavouriteRestaurants;
import com.tip.lunchbox.model.server.request.Login;
import com.tip.lunchbox.model.server.request.SignUp;
import com.tip.lunchbox.model.server.response.CommentsResponse;
import com.tip.lunchbox.model.server.response.FavouriteRestaurantsResponse;
import com.tip.lunchbox.model.server.response.RefreshResponse;
import com.tip.lunchbox.model.server.response.Tokens;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("login")
    public Observable<Tokens> loginUser(@Body Login login);

    @POST("signup")
    public Observable<Tokens> signUpUser(@Body SignUp signUp);

    @POST("fav")
    public Observable<String> setUserFav(@Body FavouriteRestaurants favouriteRestaurants);

    @POST("comments")
    public Observable<String> comment(@Body Comment comment);

    @GET("comments")
    public Observable<CommentsResponse> getComment(@Query("resID") Integer resId);

    @GET("fav")
    public Observable<FavouriteRestaurantsResponse> getUserFav(@Query("username") String username);

    @GET("refresh")
    public Observable<RefreshResponse> refresh();
}
