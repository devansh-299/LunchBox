package com.tip.lunchbox.view.fragment;

import android.Manifest;
import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.chip.Chip;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.tip.lunchbox.R;
import com.tip.lunchbox.databinding.FragmentHomeBinding;
import com.tip.lunchbox.model.zomato.CategoryContainer;
import com.tip.lunchbox.model.zomato.Restaurant;
import com.tip.lunchbox.model.zomato.RestaurantContainer;
import com.tip.lunchbox.utilities.Constants;
import com.tip.lunchbox.utilities.LocationHelper;
import com.tip.lunchbox.utilities.SharedPreferencesUtil;
import com.tip.lunchbox.view.activity.RestaurantDetails;
import com.tip.lunchbox.view.listeners.CategoryChangeListener;
import com.tip.lunchbox.view.adapter.RestaurantAdapter;
import com.tip.lunchbox.view.listeners.RecyclerTouchListener;
import com.tip.lunchbox.viewmodel.HomeViewModel;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeFragment extends Fragment implements OnMapReadyCallback {

    private double userLocationLatitude;
    private double userLocationLongitude;
    HomeViewModel viewModel;
    FragmentHomeBinding homeBinding;
    RestaurantAdapter adapter;
    ArrayList<Restaurant.MapInfo> mapInfoArrayList;
    SupportMapFragment supportMapFragment;
    CategoryChangeListener categoryChangeListener;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false);
        // Getting ViewModel From BackStack
        NavController navController = NavHostFragment.findNavController(this);
        NavBackStackEntry navBackStackEntry = navController.getBackStackEntry(R.id.homeFragment);
        viewModel = new ViewModelProvider(navBackStackEntry).get(HomeViewModel.class);

        adapter = new RestaurantAdapter(getActivity());

        new RecyclerTouchListener(getActivity(), homeBinding.rvRestaurant, (view, position) -> {
            Intent intent = new Intent(getContext(), RestaurantDetails.class);
            intent.putExtra(Constants.INTENT_RES_ID, adapter.getData().get(position)
                    .getRestaurant().getId());
            requireActivity().startActivity(intent);
        });

        BottomSheetBehavior.from(homeBinding.bsRestaurantList);
        homeBinding.rvRestaurant.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeBinding.rvRestaurant.setAdapter(adapter);
        supportMapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_view);
        return homeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@androidx.annotation.NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        if (checkLocationPermissionsGranted()) {
            checkLocationSettings();
        } else {
            requestLocationPermissions();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        homeBinding = null;
        compositeDisposable.clear();
    }

    // Checks if the permissions for accessing coarse and fine locations are granted.
    private boolean checkLocationPermissionsGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return ContextCompat.checkSelfPermission(getContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(getContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        } else {
            return true;
        }
    }

    // Relays a request to the user for granting the required permissions to access location.
    private void requestLocationPermissions() {
        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                                        Manifest.permission.ACCESS_FINE_LOCATION},
                Constants.REQUEST_LOCATION_PERMISSION);
    }

    /* Checks if required location service settings like GPS/Wifi/Cellular are enabled.
    If not, a dialog will appear asking for the same to be turned on.
     */
    private void checkLocationSettings() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        SettingsClient client = LocationServices.getSettingsClient(getContext());
        client.checkLocationSettings(builder.build())
                .addOnSuccessListener(getActivity(), locationSettingsResponse ->
                        getCurrentLocation())
                .addOnFailureListener(getActivity(), e -> {
                    if (e instanceof ResolvableApiException) {
                        try {
                            ResolvableApiException resolvable = (ResolvableApiException) e;
                            startIntentSenderForResult(resolvable.getResolution().getIntentSender(),
                                    Constants.REQUEST_CHECK_SETTINGS, null, 0, 0, 0, null);
                        } catch (IntentSender.SendIntentException sendEx) {
                            // Ignore the error.
                        }
                    }
                });
    }

    /* Requests the current location of the user (all other permission and location service checks
    should be done before calling this method, which is also why the lint error is suppressed)
     */

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
        FusedLocationProviderClient fusedLocationClient = LocationServices
                .getFusedLocationProviderClient(getContext());
        fusedLocationClient.getCurrentLocation(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY,
                null)
                .addOnSuccessListener(getActivity(), location -> {
                    if (location != null) {
                        userLocationLatitude = location.getLatitude();
                        userLocationLongitude = location.getLongitude();
                        addObservableForCategoryFilter();
                        loadData();
                        clearFilterAction();
                    } else {
                        Log.e("Location", "Null");
                    }
                });
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
        viewModel.getCategoriesLiveData().observe(getViewLifecycleOwner(), categoryResponse -> {
            if (categoryResponse != null) {
                List<CategoryContainer> categories = categoryResponse.getCategories();
                for (CategoryContainer categoryContainer : categories) {
                    Chip categoryChip = (Chip) (getLayoutInflater()
                            .inflate(R.layout.item_chip_category, homeBinding.filterChipGroup,
                                    false));
                    categoryChip.setText(categoryContainer.getCategories().getName());
                    categoryChip.setTag(categoryContainer.getCategories().getId());
                    categoryChip.setOnCheckedChangeListener((buttonView, isChecked) -> {
                        if (isChecked) {
                            categoryChangeListener.onFilterChanged((Integer) categoryChip.getTag());
                        }
                    });
                    homeBinding.filterChipGroup.addView(categoryChip);
                }
                // Trigger appearance and disappearance of the 'Clear Filter' chip
                homeBinding.filterChipGroup.setOnCheckedChangeListener((group, checkedId) -> {
                    if (checkedId != -1 && homeBinding.clearFilterChip.getVisibility()
                            == View.INVISIBLE) {
                        homeBinding.clearFilterChip.setAlpha(0f);
                        homeBinding.clearFilterChip.setVisibility(View.VISIBLE);
                        homeBinding.clearFilterChip.animate().alpha(1f).setListener(null);
                    } else if (checkedId == -1) {
                        homeBinding.clearFilterChip.animate().alpha(0f)
                                .setListener(new Animator.AnimatorListener() {
                                    /*
                                    Simple animation for the appearing and disappearing of the
                                    'Clear Filter' chip
                                    */
                                    @Override
                                    public void onAnimationStart(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        homeBinding.clearFilterChip.setVisibility(View.INVISIBLE);
                                    }

                                    @Override
                                    public void onAnimationCancel(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationRepeat(Animator animation) {

                                    }
                                });
                    }
                });
            }
        });

        viewModel.getRestaurantLiveData(userLocationLatitude, userLocationLongitude)
                .observe(getViewLifecycleOwner(), geoCodeResponse -> {
                    if (geoCodeResponse != null) {
                        // Save City Id for later use
                        SharedPreferencesUtil.setStringPreference(
                                getActivity(),
                                Constants.PREF_USER_CITY_ID,
                                geoCodeResponse.getLocality().getCityId());

                        adapter.setData(geoCodeResponse.getNearbyRestaurantContainers());
                        showData();
                        homeBinding.appBarTvLocation.setText(
                                geoCodeResponse.getLocality().getTitle()
                                        + ", " + geoCodeResponse.getLocality().getCityName());
                        setMapMarkers(geoCodeResponse.getNearbyRestaurantContainers());
                    } else {
                        showErrorView();
                    }
                });

        viewModel.getFilteredLiveData().observe(getViewLifecycleOwner(), searchResponse -> {
            if (searchResponse != null) {
                adapter.setData(searchResponse.getRestaurantContainers());
                showData();

                setMapMarkers(searchResponse.getRestaurantContainers());
            } else {
                showErrorView();
            }
        });
    }

    // Emits an integer (categoryId) whenever any category filter chip is selected
    private void addObservableForCategoryFilter() {
        Observable.create((ObservableOnSubscribe<Integer>) emitter ->
                categoryChangeListener = emitter::onNext)
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {
                        compositeDisposable.add(disposable);
                    }

                    @Override
                    public void onNext(@NonNull Integer id) {
                        showLoadingView();
                        viewModel.fetchFilteredRestaurantData(
                                id, userLocationLatitude, userLocationLongitude);
                    }

                    @Override
                    public void onError(@NonNull Throwable error) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    // This methods clears the selected filter and fetches the unfiltered nearby restaurant data
    private void clearFilterAction() {
        homeBinding.clearFilterChip.setOnClickListener(v -> {
            homeBinding.filterChipGroup.clearCheck();
            viewModel.fetchRestaurantLiveData(userLocationLatitude, userLocationLongitude);
        });
    }

    private void setMapMarkers(List<RestaurantContainer> restaurantContainerList) {
        mapInfoArrayList = new ArrayList<>();
        for (RestaurantContainer restaurantContainer : restaurantContainerList) {
            this.mapInfoArrayList.add(restaurantContainer.getRestaurant().getMapInfo());
        }
        supportMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        // Clears the existing markers every time a new filter is selected or cleared
        googleMap.clear();

        LatLng location = new LatLng(userLocationLatitude, userLocationLongitude);
        try {
            String cityName = LocationHelper.getUserAddressLine(
                    getActivity(),
                    userLocationLatitude,
                    userLocationLongitude,
                    0);
            googleMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_home_marker))
                    .position(location).title(cityName));
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        // If the mapInfoArrayList has entries, the google maps camera is zoomed to an average of
        // the latitude and longitude coordinates of the locations
        double latSum = 0;
        double longSum = 0;
        double latAvg;
        double longAvg;

        if (mapInfoArrayList != null && mapInfoArrayList.size() != 0) {
            for (Restaurant.MapInfo latLng : this.mapInfoArrayList) {
                latSum += latLng.getLatLng().latitude;
                longSum += latLng.getLatLng().longitude;
                googleMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker))
                        .position(latLng.getLatLng())
                        .title(latLng.getName())
                        .snippet(latLng.getDesc()));
            }
            latAvg = latSum / (this.mapInfoArrayList.size());
            longAvg = longSum / (this.mapInfoArrayList.size());
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latAvg, longAvg),
                    (float) 12));
        } else {
            Toast.makeText(requireContext(), R.string.no_results_found, Toast.LENGTH_SHORT).show();
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, (float) 15));
        }
    }

    // Callback to handle appropriate permission requests
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @androidx.annotation.NonNull String[] permissions,
                                           @androidx.annotation.NonNull int[] grantResults) {
        Log.d("GrantResults", "" + grantResults.length);
        if (requestCode == Constants.REQUEST_LOCATION_PERMISSION) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                checkLocationSettings();
            } else {
                new MaterialAlertDialogBuilder(getContext())
                        .setTitle(getString(R.string.location_permission))
                        .setMessage(getString(R.string.location_permission_description))
                        .setPositiveButton(getString(R.string.proceed), (dialog, which) -> {
                            dialog.dismiss();
                            requestLocationPermissions();
                        })
                        .setNegativeButton(getString(R.string.cancel), ((dialog, which) ->
                                System.exit(0)))
                        .setCancelable(false)
                        .show();
            }
        }
    }

    // Callback to handle results from intent
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == Constants.REQUEST_CHECK_SETTINGS) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    getCurrentLocation();
                    break;
                case Activity.RESULT_CANCELED:
                    new MaterialAlertDialogBuilder(getContext())
                            .setTitle(getString(R.string.gps_service))
                            .setMessage(getString(R.string.gps_service_description))
                            .setPositiveButton(getString(R.string.proceed), (dialog, which) -> {
                                dialog.dismiss();
                                checkLocationSettings();
                            })
                            .setNegativeButton(getString(R.string.cancel), ((dialog, which) ->
                                    System.exit(0)))
                            .setCancelable(false)
                            .show();
                    break;
                default: break;
            }
        }
    }
}