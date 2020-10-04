package com.tip.lunchbox.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;

import com.tip.lunchbox.databinding.FragmentSearchBinding;
import com.tip.lunchbox.view.adapter.CollectionsAdapter;
import com.tip.lunchbox.view.adapter.RestaurantAdapter;
import com.tip.lunchbox.viewmodel.SearchFragmentViewModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class SearchFragment extends Fragment implements View.OnClickListener {

    FragmentSearchBinding binding;
    CollectionsAdapter collectionsadapter;
    RestaurantAdapter searchadapter;
    SearchFragmentViewModel viewModel;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(SearchFragmentViewModel.class);
        setUpRecyclerViews();
        addObservabletoSearchView();
        binding.searchView.setOnClickListener(this);
        // Observing data
        viewModel.getCollectionsLiveData().observe(
                getViewLifecycleOwner(),
                collectionsResponse -> collectionsadapter.setData(
                        collectionsResponse.getCollectionsContainer()));

        viewModel.getSearchResponseLivedata().observe(
                getViewLifecycleOwner(),
                searchResponse -> searchadapter.setData(searchResponse.getRestaurantContainers())
        );
        return binding.getRoot();
    }

    public void setUpRecyclerViews() {

        //Collections RV
        collectionsadapter = new CollectionsAdapter(requireActivity());
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        binding.rvCollections.setLayoutManager(
                new LinearLayoutManager(
                        getActivity(),
                        LinearLayoutManager.HORIZONTAL, false));
        binding.rvCollections.setAdapter(collectionsadapter);
        pagerSnapHelper.attachToRecyclerView(binding.rvCollections);

        //Search rv
        searchadapter = new RestaurantAdapter(requireActivity());
        binding.rvSearch.setAdapter(searchadapter);
        binding.rvSearch.setLayoutManager(new LinearLayoutManager(requireActivity()));
    }

    public void addObservabletoSearchView() {
        Observable.create((ObservableOnSubscribe<String>)
                emitter -> binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        viewModel.FetchSearchResponseLiveData(query);
                        Log.d("http", "onQueryTextSubmit: " + query);
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        if (!emitter.isDisposed())
                            emitter.onNext(newText);
                        Log.d("http", "onTextSubmit: " + newText);
                        return false;
                    }
                }))
                .debounce(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        if (!s.isEmpty())
                            viewModel.FetchSearchResponseLiveData(s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if (v == binding.searchView) {
            binding.getRoot().smoothScrollTo(v.getTop(), v.getLeft());
        }
    }
}