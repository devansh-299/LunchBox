package com.tip.lunchbox.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.tip.lunchbox.data.Repository;
import com.tip.lunchbox.data.SearchQuery;
import com.tip.lunchbox.model.SearchResponse;
import java.util.HashMap;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class HomeViewModel extends ViewModel {

    MutableLiveData<SearchResponse> restaurantLiveData = new MutableLiveData<>();
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public LiveData<SearchResponse> getRestaurantLiveData() {
        if(restaurantLiveData.getValue() == null) {
            FetchRestaurantLiveData();
        }
        return restaurantLiveData;
    }

    public void FetchRestaurantLiveData() {

        Repository repository = new Repository();
        HashMap<String, String> queryParams = new HashMap<>();
        /*
            For now, using temporary query parameters
         */
        queryParams.put("entity_id", "5");
        queryParams.put("entity_type", "city");

        repository.getSearchResponseObservable(new SearchQuery().addEntity(new SearchQuery.Entity().city(5)))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<SearchResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull SearchResponse searchResponse) {
                        restaurantLiveData.setValue(searchResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        restaurantLiveData.setValue(null);
                    }
                } );


    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
