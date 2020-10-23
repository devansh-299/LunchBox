package com.tip.lunchbox.view.fragment;

import android.content.Intent;
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
import com.tip.lunchbox.utilities.Constants;
import com.tip.lunchbox.view.activity.CollectionDetails;
import com.tip.lunchbox.view.activity.RestaurantDetails;
import com.tip.lunchbox.view.adapter.CollectionsAdapter;
import com.tip.lunchbox.view.adapter.RestaurantAdapter;
import com.tip.lunchbox.view.listeners.RecyclerTouchListener;
import com.tip.lunchbox.viewmodel.SearchViewModel;

import org.jetbrains.annotations.NotNull;

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
    CollectionsAdapter collectionsAdapter;
    RestaurantAdapter searchAdapter;
    SearchViewModel viewModel;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        setUpRecyclerViews();
        addObservableToSearchView();
        binding.searchView.setOnClickListener(this);

        // Observing data
        viewModel.getCollectionsLiveData().observe(
                getViewLifecycleOwner(),
                collectionsResponse -> collectionsAdapter.setData(
                        collectionsResponse.getCollectionsContainer()));

        viewModel.getSearchResponseLiveData().observe(
                getViewLifecycleOwner(),
                searchResponse -> {
                    binding.llSearchRestaurants.setVisibility(View.GONE);
                    searchAdapter.setData(searchResponse.getRestaurantContainers());
                });
        return binding.getRoot();
    }

    public void setUpRecyclerViews() {

        // Collections RecyclerView
        collectionsAdapter = new CollectionsAdapter(requireActivity());
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        binding.rvCollections.setLayoutManager(
                new LinearLayoutManager(
                        getActivity(),
                        LinearLayoutManager.HORIZONTAL, false));
        binding.rvCollections.setAdapter(collectionsAdapter);
        pagerSnapHelper.attachToRecyclerView(binding.rvCollections);

        // Search RecyclerView
        searchAdapter = new RestaurantAdapter(requireActivity());
        binding.rvSearch.setAdapter(searchAdapter);
        binding.rvSearch.setLayoutManager(new LinearLayoutManager(requireActivity()));

        // Setting up on item click action
        new RecyclerTouchListener(getActivity(), binding.rvSearch, (view, position) -> {
            Intent intent = new Intent(getContext(), RestaurantDetails.class);
            intent.putExtra(Constants.INTENT_RES_ID, searchAdapter.getData().get(position)
                    .getRestaurant().getId());
            requireActivity().startActivity(intent);
        });

        new RecyclerTouchListener(getActivity(), binding.rvCollections, (view, position) -> {
            Intent intent = new Intent(getActivity(), CollectionDetails.class);
            intent.putExtra(Constants.INTENT_COLLECTION_ID,
                    collectionsAdapter.getData()
                            .get(position).getCollection().getCollectionId());

            intent.putExtra(Constants.INTENT_COLLECTION_NAME, collectionsAdapter.getData()
                    .get(position).getCollection().getTitle());
            requireActivity().startActivity(intent);
        });
    }

    public void addObservableToSearchView() {
        Observable.create((ObservableOnSubscribe<String>)
                emitter -> binding.searchView.setOnQueryTextListener(
                        new SearchView.OnQueryTextListener() {
                            @Override
                            public boolean onQueryTextSubmit(String query) {
                                viewModel.fetchSearchResponseLiveData(query);
                                Log.d("http", "onQueryTextSubmit: " + query);
                                return false;
                            }

                            @Override
                            public boolean onQueryTextChange(String newText) {
                                if (!emitter.isDisposed()) {
                                    emitter.onNext(newText);
                                }
                                Log.d("http", "onTextSubmit: " + newText);
                                return false;
                            }
                        }))
                .debounce(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {
                        compositeDisposable.add(disposable);
                    }

                    @Override
                    public void onNext(@NonNull String query) {
                        if (!query.isEmpty()) {
                            viewModel.fetchSearchResponseLiveData(query);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {

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
    public void onClick(View view) {
        if (view == binding.searchView) {
            binding.nestedScrollView.smoothScrollTo(view.getTop(), view.getLeft());
        }
    }
}