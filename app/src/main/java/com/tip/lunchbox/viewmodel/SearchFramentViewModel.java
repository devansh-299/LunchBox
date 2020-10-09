package com.tip.lunchbox.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tip.lunchbox.data.Repository;
import com.tip.lunchbox.model.CollectionsResponse;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchFramentViewModel extends ViewModel {

    MutableLiveData<CollectionsResponse> collectionsResponseLiveData = new MutableLiveData();
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public LiveData<CollectionsResponse> getCollectionsLiveData() {
        if (collectionsResponseLiveData.getValue() == null) {
            fetchCollectionsLiveData();
        }
        return collectionsResponseLiveData;
    }

    private void fetchCollectionsLiveData() {
        Repository repository = new Repository();

        repository.getCollectionsResponseObservable(5).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<CollectionsResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {
                        compositeDisposable.add(disposable);
                    }

                    @Override
                    public void onSuccess(@NonNull CollectionsResponse collectionsResponse) {
                        collectionsResponseLiveData.setValue(collectionsResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {
                    //TODO add onError Body
                    }
                });
    }

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }
}
