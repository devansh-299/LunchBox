package com.tip.lunchbox.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tip.lunchbox.data.Repository;
import com.tip.lunchbox.model.GeocodeResponse;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class HomeViewModel extends ViewModel {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Repository repository = new Repository();
    private MutableLiveData<GeocodeResponse> restaurantLiveData = new MutableLiveData<>();

    public LiveData<GeocodeResponse> getRestaurantLiveData() {
        fetchRestaurantLiveData(1.1, 1.1);
        return restaurantLiveData;
    }

    public void fetchRestaurantLiveData(double lat, double lon) {

        /*
            For now, using temporary query parameters
         */

        repository.getGeocodeResponseObservable(40.71, 74.00)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<GeocodeResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {
                        compositeDisposable.add(disposable);
                    }

                    @Override
                    public void onSuccess(@NonNull GeocodeResponse geocodeResponse) {
                        restaurantLiveData.setValue(geocodeResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable error) {

                    }
                });

    }


    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
