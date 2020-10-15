package com.tip.lunchbox.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tip.lunchbox.data.Repository;
import com.tip.lunchbox.model.Restaurant;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RestaurantDetailsViewModel extends ViewModel {
    private MutableLiveData<Restaurant> restaurantLiveData = new MutableLiveData<>();
    Repository repository = new Repository();
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public LiveData<Restaurant> getRestaurantLiveData(int resId) {
        fetchRestaurantLiveData(resId);
        return restaurantLiveData;
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
