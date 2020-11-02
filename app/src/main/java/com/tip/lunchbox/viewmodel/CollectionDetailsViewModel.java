package com.tip.lunchbox.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tip.lunchbox.data.Repository;
import com.tip.lunchbox.data.zomato.SearchQuery;
import com.tip.lunchbox.model.zomato.SearchResponse;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CollectionDetailsViewModel extends ViewModel {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Repository repository = new Repository();
    private MutableLiveData<SearchResponse> searchResponseLiveData = new MutableLiveData<>();

    public LiveData<SearchResponse> getSearchResponseLiveData(int categoryId, int cityId) {
        fetchSearchResponseLiveData(categoryId, cityId);
        return searchResponseLiveData;
    }

    public void fetchSearchResponseLiveData(int categoryId, int cityId) {
        repository.getSearchResponseObservable(new SearchQuery()
                .addEntity(new SearchQuery.Entity().city(cityId)).addCollection(categoryId))
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
                        searchResponseLiveData.setValue(null);
                    }
                });
    }
}