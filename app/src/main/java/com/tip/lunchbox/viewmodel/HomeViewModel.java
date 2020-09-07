package com.tip.lunchbox.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tip.lunchbox.data.Repository;
import com.tip.lunchbox.model.SearchResponse;

import java.util.HashMap;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.disposables.DisposableContainer;
import io.reactivex.rxjava3.schedulers.Schedulers;

// sample view model
public class HomeViewModel extends ViewModel {
    String TAG = "yeu";
    MutableLiveData<SearchResponse> RestaurantLivedata = new MutableLiveData<>();
    CompositeDisposable disposableContainer = new CompositeDisposable();

    public LiveData<SearchResponse> getRestaurantLivedata() {
        //if (RestaurantLivedata.getValue()==null)
            FetchRestaurantLiveData();
        return RestaurantLivedata;
    }
    public void FetchRestaurantLiveData(){

        Repository repository = new Repository();
        HashMap<String, String> Queryparams = new HashMap<>();
        Queryparams.put("entity_id","5");
        Queryparams.put("entity_type","city");
        repository.getSearchResponseObservable(Queryparams)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposableContainer.add(d);
                    }

                    @Override
                    public void onNext(@NonNull SearchResponse searchResponse) {
                      RestaurantLivedata.setValue(searchResponse);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {


                    }
                });

    }
}
