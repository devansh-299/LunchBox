package com.tip.lunchbox.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tip.lunchbox.data.Repository;
import com.tip.lunchbox.data.SearchQuery;
import com.tip.lunchbox.model.CollectionsResponse;
import com.tip.lunchbox.model.SearchResponse;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchViewModel extends ViewModel {

    MutableLiveData<CollectionsResponse> collectionsResponseLivedata = new MutableLiveData<>();
    MutableLiveData<SearchResponse> searchResponseLiveData = new MutableLiveData<>();
    CompositeDisposable compositeDisposable = new CompositeDisposable();


    public LiveData<CollectionsResponse> getCollectionsLiveData() {
        if (collectionsResponseLivedata.getValue() == null) {
            fetchCollectionsLiveData();
        }
        return collectionsResponseLivedata;
    }

    public LiveData<SearchResponse> getSearchResponseLivedata() {
        return searchResponseLiveData;
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
                        collectionsResponseLivedata.setValue(collectionsResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {
                        //TODO add onError Body
                    }
                });
    }

    public void fetchSearchResponseLiveData(String query) {
        Repository repository = new Repository();
        repository.getSearchResponseObservable(
                new SearchQuery().addQuery("Pune")).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<SearchResponse>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {
                        compositeDisposable.add(disposable);
                    }

                    @Override
                    public void onSuccess(@NonNull SearchResponse searchResponse) {
                        searchResponseLiveData.setValue(searchResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {

                    }
                });
    }

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }
}
