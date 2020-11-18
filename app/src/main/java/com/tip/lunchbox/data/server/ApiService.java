package com.tip.lunchbox.data.server;

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

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("login")
    public Single<Tokens> loginUser(@Body Login login);

    @POST("signup")
    public Single<Tokens> signUpUser(@Body SignUp signUp);

    @POST("fav")
    public Single<CustomResponse> postUserFav(@Body FavouriteRestaurants favouriteRestaurants);

    @POST("comments")
    public Single<CustomResponse> postComment(@Body Comment comment);

    @GET("restaurant/comments")
    public Single<CommentsResponseContainer> getComment(@Query("resID") String resId);

    @GET("user/comments")
    public Single<CommentsResponseContainer> getUserComments(@Query("user") String user);

    @GET("fav")
    public Single<FavouriteRestaurantsResponse> getUserFav(@Query("username") String username);

    @GET("refresh")
    public Single<RefreshResponse> refresh();
}
