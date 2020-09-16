package com.tip.lunchbox.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.tip.lunchbox.view.adapter.RestaurantAdapter;
import com.tip.lunchbox.databinding.FragmentHomeBinding;
import com.tip.lunchbox.viewmodel.HomeViewModel;

public class HomeFragment extends Fragment {

    HomeViewModel viewModel;
    FragmentHomeBinding homeBinding;
    RestaurantAdapter adapter;
    private BottomSheetBehavior mBottomSheetBehavior;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false);

        View view = homeBinding.getRoot();
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        adapter = new RestaurantAdapter(getActivity());
        mBottomSheetBehavior = BottomSheetBehavior.from(homeBinding.nsvRestaurantList);
        homeBinding.rvRestaurant.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeBinding.rvRestaurant.setAdapter(adapter);
        loadData();
        return view;
    }

    private void showLoadingView() {
        homeBinding.rvRestaurant.setVisibility(View.GONE);
        homeBinding.llErrorOccurred.setVisibility(View.GONE);
        homeBinding.pbLoading.setVisibility(View.VISIBLE);
    }

    private void showErrorView() {
        homeBinding.rvRestaurant.setVisibility(View.GONE);
        homeBinding.pbLoading.setVisibility(View.GONE);
        homeBinding.llErrorOccurred.setVisibility(View.VISIBLE);
    }

    private void showData() {
        homeBinding.pbLoading.setVisibility(View.GONE);
        homeBinding.llErrorOccurred.setVisibility(View.GONE);
        homeBinding.rvRestaurant.setVisibility(View.VISIBLE);
    }


    private void loadData() {
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