package com.tip.lunchbox.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tip.lunchbox.data.Repository;
import com.tip.lunchbox.data.zomato.SearchQuery;
import com.tip.lunchbox.model.zomato.CategoryResponse;
import com.tip.lunchbox.model.zomato.GeocodeResponse;
import com.tip.lunchbox.model.zomato.SearchResponse;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class HomeViewModel extends ViewModel {

    private static final String TAG = "HomeViewModel";
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Repository repository = new Repository();
    private MutableLiveData<GeocodeResponse> restaurantLiveData = new MutableLiveData<>();
    private MutableLiveData<CategoryResponse> categoriesLiveData = new MutableLiveData<>();
    private MutableLiveData<SearchResponse> filteredRestaurantLiveData = new MutableLiveData<>();

    public LiveData<CategoryResponse> getCategoriesLiveData() {
        // Since list of categories will rarely change, previous data is used as much is possible
        if (categoriesLiveData.getValue() == null) {
            fetchCategoriesLiveData();
        }
        return categoriesLiveData;
    }

    public LiveData<GeocodeResponse> getRestaurantLiveData(Double lat, Double lon) {
        fetchRestaurantLiveData(lat, lon);
        return restaurantLiveData;
    }

    public LiveData<SearchResponse> getFilteredLiveData() {
        return filteredRestaurantLiveData;
    }

    /**
     * Since there is no usage of UI logic here, the {@link Single#observeOn(Scheduler)} is omitted
     * and {@link MutableLiveData#postValue(Object)} is used to set the value on the MutableLiveData
     * object from a background thread. A similar logic is used for the other fetch methods here.
     */
    private void fetchCategoriesLiveData() {
        repository.getCategoryResponseObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<CategoryResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {
                        compositeDisposable.add(disposable);
                    }

                    @Override
                    public void onSuccess(@NonNull CategoryResponse categoryResponse) {
                        categoriesLiveData.postValue(categoryResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable error) {

                    }
                });
    }

    public void fetchRestaurantLiveData(double lat, double lon) {
        repository.getGeocodeResponseObservable(lat, lon)
                .subscribeOn(Schedulers.newThread())
                .subscribe(new SingleObserver<GeocodeResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {
                        compositeDisposable.add(disposable);
                    }

                    @Override
                    public void onSuccess(@NonNull GeocodeResponse geocodeResponse) {
                        restaurantLiveData.postValue(geocodeResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable error) {
                        Log.e(TAG, error.getMessage());
                    }
                });

    }

    public void fetchFilteredRestaurantData(Integer categoryId, Double lat, Double lon) {
        repository.getSearchResponseObservable(new SearchQuery().addCategory(categoryId)
                .addGeoLocation(lat, lon))
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<SearchResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {
                        compositeDisposable.add(disposable);
                    }

                    @Override
                    public void onSuccess(@NonNull SearchResponse searchResponse) {
                        filteredRestaurantLiveData.postValue(searchResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable error) {
                        Log.e(TAG, error.getMessage());
                    }
                });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
