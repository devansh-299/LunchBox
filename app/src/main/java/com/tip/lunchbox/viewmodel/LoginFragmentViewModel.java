package com.tip.lunchbox.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.tip.lunchbox.LunchBoxApplication;
import com.tip.lunchbox.data.Repository;
import com.tip.lunchbox.model.server.request.Login;
import com.tip.lunchbox.model.server.response.CustomResponse;
import com.tip.lunchbox.model.server.response.Tokens;
import com.tip.lunchbox.utilities.Constants;
import com.tip.lunchbox.utilities.SharedPreferencesUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.HttpException;

public class LoginFragmentViewModel extends ViewModel {
    MutableLiveData<Boolean> isValid = new MutableLiveData<>();
    private String errorMessage;
    private final Repository repository = new Repository();
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public LiveData<Boolean> loginUserLiveData() {
        return isValid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void loginUser(Login login) {
        repository.loginUser(login).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Tokens>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {
                        compositeDisposable.add(disposable);
                        isValid.setValue(null);
                    }

                    @Override
                    public void onSuccess(@NonNull Tokens tokens) {

                        //Using set value because we don't want a delay in logging the user in.
                        SharedPreferencesUtil.setStringPreference(LunchBoxApplication.getContext(),
                                Constants.PREF_AUTH_TOKEN, tokens.getAuthToken());
                        SharedPreferencesUtil.setStringPreference(LunchBoxApplication.getContext(),
                                Constants.PREF_REFRESH_TOKEN, tokens.getRefreshToken());
                        isValid.setValue(true);

                    }

                    @Override
                    public void onError(@NonNull Throwable error) {
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
                            } catch (IOException exception) {
                                errorMessage = "Something went wrong";
                                exception.printStackTrace();
                            }
                            Log.d("http", "onError: " + error);
                        } else {
                            errorMessage = "Something went wrong";
                        }
                        isValid.setValue(false);
                    }
                });
    }
}
