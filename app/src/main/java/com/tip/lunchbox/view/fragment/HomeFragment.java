package com.tip.lunchbox.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
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
    ArrayList<Restaurant.Mapinfo> latlangs;
    SupportMapFragment supportMapFragment;
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
        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_view);
        supportMapFragment.getMapAsync(this);
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
                setLatLangs(searchResponse);
            } else {
                showErrorView();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng location = new LatLng(18.5, 73.8);
        googleMap.addMarker(new MarkerOptions()
        .position(location)
        .title("Pune")
        );
        if ( latlangs != null )
        for (Restaurant.Mapinfo latLng: this.latlangs){
            googleMap.addMarker(new MarkerOptions().position(latLng.getLatLng()).title(latLng.getName()).snippet(latLng.getDesc()));
        }

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, (float) 15));


    }
    private void setLatLangs (SearchResponse response){
        latlangs = new ArrayList<>();
        for ( RestaurantContainer restaurantContainer: response.getRestaurantContainers() )  {

            this.latlangs.add(restaurantContainer.getRestaurant().getMapinfo());
        }
        supportMapFragment.getMapAsync(this);

    }

}