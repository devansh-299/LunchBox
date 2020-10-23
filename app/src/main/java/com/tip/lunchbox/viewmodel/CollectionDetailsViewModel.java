package com.tip.lunchbox.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tip.lunchbox.data.Repository;
import com.tip.lunchbox.data.SearchQuery;
import com.tip.lunchbox.model.SearchResponse;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CollectionDetailsViewModel extends ViewModel {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<SearchResponse> searchResponseLiveData = new MutableLiveData<>();

    public LiveData<SearchResponse> getSearchResponseLiveData(int categoryId) {
        fetchSearchResponseLiveData(categoryId);
        return searchResponseLiveData;
    }

    public void fetchSearchResponseLiveData(int categoryId) {
        Repository repository = new Repository();
        repository.getSearchResponseObservable(new SearchQuery()
                .addEntity(new SearchQuery.Entity().city(5)).addCollection(categoryId))
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
}