package com.tip.lunchbox.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tip.lunchbox.data.Repository;
import com.tip.lunchbox.model.server.response.CommentsResponseContainer;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UserFragmentViewModel extends ViewModel {
    private Repository repository = new Repository();
    private final MutableLiveData<CommentsResponseContainer> commentsResponseLiveData
            = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public LiveData<CommentsResponseContainer> getCommentsResponseLiveData(String username) {
        getUserComments(username);
        return commentsResponseLiveData;
    }

    public void getUserComments(String username) {
        repository.getUserComments(username)
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

    @Override
    protected void onCleared() {
        compositeDisposable.dispose();
        super.onCleared();
    }
}

