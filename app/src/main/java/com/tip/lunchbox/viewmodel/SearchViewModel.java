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

    MutableLiveData<CollectionsResponse> collectionsResponseLiveData = new MutableLiveData<>();
    MutableLiveData<SearchResponse> searchResponseLiveData = new MutableLiveData<>();
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    Repository repository = new Repository();

    public LiveData<CollectionsResponse> getCollectionsLiveData() {
        if (collectionsResponseLiveData.getValue() == null) {
            fetchCollectionsLiveData();
        }
        return collectionsResponseLiveData;
    }

    public LiveData<SearchResponse> getSearchResponseLiveData() {
        return searchResponseLiveData;
    }

    private void fetchCollectionsLiveData() {
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

    public void fetchSearchResponseLiveData(String query) {
        repository.getSearchResponseObservable(new SearchQuery()
                .addEntity(new SearchQuery.Entity().city(5)).addQuery(query))
                .subscribeOn(Schedulers.newThread())
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
