package com.tip.lunchbox.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.tip.lunchbox.data.Repository;
import com.tip.lunchbox.model.server.request.Comment;
import com.tip.lunchbox.model.server.response.CommentsResponseContainer;
import com.tip.lunchbox.model.server.response.CustomResponse;
import com.tip.lunchbox.model.zomato.Restaurant;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.HttpException;

public class RestaurantDetailsViewModel extends ViewModel {
    private final MutableLiveData<Restaurant> restaurantLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isError = new MutableLiveData<>();
    private final MutableLiveData<CommentsResponseContainer> commentsResponseLiveData
            = new MutableLiveData<>();
    private String errorMessage;
    Repository repository = new Repository();
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public String getErrorMessage() {
        return errorMessage;
    }

    public LiveData<Restaurant> getRestaurantLiveData(int resId) {
        fetchRestaurantLiveData(resId);
        return restaurantLiveData;
    }

    public LiveData<CommentsResponseContainer> getCommentsResponseLiveData(String resId) {
        getComments(resId);
        return commentsResponseLiveData;
    }

    public LiveData<Boolean> postCommentLiveData() {
        return isError;
    }

    public void getComments(String resId) {
        repository.getComments(resId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<CommentsResponseContainer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {
                        compositeDisposable.add(disposable);
                    }

                    @Override
                    public void onSuccess(@NonNull CommentsResponseContainer commentsResponses) {
                        commentsResponseLiveData.setValue(commentsResponses);
                    }

                    @Override
                    public void onError(@NonNull Throwable error) {
                        Log.d("http", "onError: " + error);
                    }
                });
    }

    public void postComment(Comment comment) {
        repository.postComment(comment)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<CustomResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {
                        compositeDisposable.add(disposable);
                        isError.setValue(null);
                    }

                    @Override
                    public void onSuccess(@NonNull CustomResponse customResponse) {
                        isError.setValue(true);
                    }

                    @Override
                    public void onError(@NonNull Throwable error) {
                        // TODO make a util class that handles exceptions like this
                        if (error instanceof IOException) {
                            errorMessage = "Please Check Network Connection";
                        }
                        if (error instanceof TimeoutException) {
                            errorMessage = "Request timed out please try again";
                        }
                        if (error instanceof HttpException) {
                            HttpException httpError = (HttpException) error;
                            try {
                                errorMessage = new Gson()
                                        .fromJson(httpError.response()
                                                .errorBody().string(), CustomResponse.class)
                                        .getMessage();
                            } catch (Exception exception) {
                                errorMessage = "Something went wrong";
                                exception.printStackTrace();
                            }
                            Log.d("http", "onError: " + error);
                        } else {
                            errorMessage = "Something went wrong";
                        }
                        isError.setValue(false);
                    }
                });
    }

    private void fetchRestaurantLiveData(int resId) {
        repository.getRestaurantResponseObservable(resId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Restaurant>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {
                        compositeDisposable.add(disposable);
                    }

                    @Override
                    public void onSuccess(@NonNull Restaurant restaurant) {
                        restaurantLiveData.setValue(restaurant);
                    }

                    @Override
                    public void onError(@NonNull Throwable error) {
                        //Handle Error
                    }
                });
    }

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }
}
