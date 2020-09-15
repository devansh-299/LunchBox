package com.tip.lunchbox.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tip.lunchbox.view.adapter.RestaurantAdapter;
import com.tip.lunchbox.databinding.FragmentHomeBinding;
import com.tip.lunchbox.viewmodel.HomeViewModel;


public class HomeFragment extends Fragment {

    HomeViewModel viewModel;
    FragmentHomeBinding homeBinding;
    RestaurantAdapter adapter ;

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
        viewModel.getRestaurantLiveData().observe(getViewLifecycleOwner(), searchResponse -> {

            adapter = new RestaurantAdapter(getActivity(), searchResponse.getRestaurantContainers());
            homeBinding.restaurantrv.setAdapter(adapter);
            homeBinding.restaurantrv.setLayoutManager(new LinearLayoutManager(getActivity()));

        });

        return view;
    }
}