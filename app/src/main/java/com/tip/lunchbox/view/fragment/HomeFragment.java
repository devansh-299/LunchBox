package com.tip.lunchbox.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tip.lunchbox.adapters.RVRestaurantAdapter;
import com.tip.lunchbox.databinding.FragmentHomeBinding;
import com.tip.lunchbox.model.RestaurantContainer;
import com.tip.lunchbox.model.SearchResponse;
import com.tip.lunchbox.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    HomeViewModel viewModel;
    FragmentHomeBinding homeBinding;
    RVRestaurantAdapter adapter ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        homeBinding = FragmentHomeBinding.inflate( inflater ,container ,false);
        View view = homeBinding.getRoot();

        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        viewModel.getRestaurantLivedata().observe(getViewLifecycleOwner(), searchResponse -> {

            adapter = new RVRestaurantAdapter(getActivity(), searchResponse.getRestaurantContainers());
            homeBinding.restaurantrv.setAdapter(adapter);
            homeBinding.restaurantrv.setLayoutManager(new LinearLayoutManager(getActivity()));

        });

        return view;
    }
}