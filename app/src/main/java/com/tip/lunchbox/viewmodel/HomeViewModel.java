package com.tip.lunchbox.viewmodel;

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
import io.reactivex.rxjava3.schedulers.Schedulers;


public class HomeViewModel extends ViewModel {
    MutableLiveData<SearchResponse> restaurantLiveData = new MutableLiveData<>();
    CompositeDisposable disposableContainer = new CompositeDisposable();

    public LiveData<SearchResponse> getRestaurantLiveData() {
        if(restaurantLiveData.getValue() == null) {
            FetchRestaurantLiveData();
        }
        return restaurantLiveData;
    }

    public void FetchRestaurantLiveData(){

        Repository repository = new Repository();
        HashMap<String, String> queryParams = new HashMap<>();
        /*
            For now, using temporary query parameters
         */
        queryParams.put("entity_id","5");
        queryParams.put("entity_type","city");

        repository.getSearchResponseObservable(queryParams)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchResponse>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposableContainer.add(d);
                    }

                    @Override
                    public void onNext(@NonNull SearchResponse searchResponse) {
                      restaurantLiveData.setValue(searchResponse);
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
