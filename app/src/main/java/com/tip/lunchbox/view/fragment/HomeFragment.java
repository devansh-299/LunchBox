package com.tip.lunchbox.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tip.lunchbox.view.adapter.RestaurantAdapter;
import com.tip.lunchbox.databinding.FragmentHomeBinding;
import com.tip.lunchbox.viewmodel.HomeViewModel;

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    HomeViewModel viewModel;
    FragmentHomeBinding homeBinding;
    RestaurantAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false);

        homeBinding.swipeRefresh.setOnRefreshListener(this);
        View view = homeBinding.getRoot();
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        adapter = new RestaurantAdapter(getActivity());

        homeBinding.rvRestaurant.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeBinding.rvRestaurant.setAdapter(adapter);
        onRefresh();
        return view;
    }

    private void showLoadingView() {
        homeBinding.rvRestaurant.setVisibility(View.GONE);
        homeBinding.llErrorOccurred.setVisibility(View.GONE);
        homeBinding.pbLoading.setVisibility(View.VISIBLE);
    }

    private void showErrorView() {
        homeBinding.swipeRefresh.setRefreshing(false);
        homeBinding.rvRestaurant.setVisibility(View.GONE);
        homeBinding.pbLoading.setVisibility(View.GONE);
        homeBinding.llErrorOccurred.setVisibility(View.VISIBLE);
    }

    private void showData() {
        homeBinding.swipeRefresh.setRefreshing(false);
        homeBinding.pbLoading.setVisibility(View.GONE);
        homeBinding.llErrorOccurred.setVisibility(View.GONE);
        homeBinding.rvRestaurant.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRefresh() {
        showLoadingView();
        viewModel.getRestaurantLiveData().observe(getViewLifecycleOwner(), searchResponse -> {
            if(searchResponse != null) {
                adapter.setData(searchResponse.getRestaurantContainers());
                showData();
            } else {
                showErrorView();
            }
        });
    }
}