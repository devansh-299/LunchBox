package com.tip.lunchbox.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.tip.lunchbox.R;
import com.tip.lunchbox.databinding.FragmentHomeBinding;
import com.tip.lunchbox.model.Restaurant;
import com.tip.lunchbox.model.RestaurantContainer;
import com.tip.lunchbox.model.SearchResponse;
import com.tip.lunchbox.view.adapter.RestaurantAdapter;
import com.tip.lunchbox.viewmodel.HomeViewModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements OnMapReadyCallback {

    HomeViewModel viewModel;
    FragmentHomeBinding homeBinding;
    RestaurantAdapter adapter;
    ArrayList<Restaurant.MapInfo> mapInfoArrayList;
    SupportMapFragment supportMapFragment;
    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false);
        // Getting ViewModel From BackStack
        NavController navController = NavHostFragment.findNavController(this);
        NavBackStackEntry navBackStackEntry = navController.getBackStackEntry(R.id.homeFragment);
        viewModel = new ViewModelProvider(navBackStackEntry).get(HomeViewModel.class);

        adapter = new RestaurantAdapter(getActivity());
        bottomSheetBehavior = BottomSheetBehavior.from(homeBinding.nsvRestaurantList);
        homeBinding.rvRestaurant.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeBinding.rvRestaurant.setAdapter(adapter);
        supportMapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_view);
        supportMapFragment.getMapAsync(this);
        loadData();
        return homeBinding.getRoot();
    }

    private void showLoadingView() {
        homeBinding.rvRestaurant.setVisibility(View.GONE);
        homeBinding.llErrorOccurred.setVisibility(View.GONE);
        homeBinding.shimmerRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorView() {
        homeBinding.rvRestaurant.setVisibility(View.GONE);
        homeBinding.shimmerRecyclerView.setVisibility(View.GONE);
        homeBinding.llErrorOccurred.setVisibility(View.VISIBLE);
    }

    private void showData() {
        homeBinding.shimmerRecyclerView.setVisibility(View.GONE);
        homeBinding.llErrorOccurred.setVisibility(View.GONE);
        homeBinding.rvRestaurant.setVisibility(View.VISIBLE);
    }

    private void loadData() {
        showLoadingView();
        viewModel.getRestaurantLiveData().observe(getViewLifecycleOwner(), searchResponse -> {
            if (searchResponse != null) {
                adapter.setData(searchResponse.getRestaurantContainers());
                showData();
                setMapMarkers(searchResponse);
            } else {
                showErrorView();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        /*
            Currently, using temporary values for testing. Ideally these values should have been
            taken from the user while first launching the application
         */
        LatLng location = new LatLng(18.5, 73.8);
        googleMap.addMarker(new MarkerOptions().position(location).title("Pune"));

        if (mapInfoArrayList != null) {
            for (Restaurant.MapInfo latLng : this.mapInfoArrayList) {
                googleMap.addMarker(new MarkerOptions()
                        .position(latLng.getLatLng())
                        .title(latLng.getName())
                        .snippet(latLng.getDesc()));
            }
        }
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, (float) 15));
    }

    private void setMapMarkers(SearchResponse response) {
        mapInfoArrayList = new ArrayList<>();
        for (RestaurantContainer restaurantContainer : response.getRestaurantContainers()) {
            this.mapInfoArrayList.add(restaurantContainer.getRestaurant().getMapInfo());
        }
        supportMapFragment.getMapAsync(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        homeBinding = null;
    }
}