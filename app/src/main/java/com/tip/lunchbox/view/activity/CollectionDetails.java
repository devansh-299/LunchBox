package com.tip.lunchbox.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.tip.lunchbox.databinding.ActivityCollectionDetailsBinding;
import com.tip.lunchbox.utilities.Constants;
import com.tip.lunchbox.utilities.SharedPreferencesUtil;
import com.tip.lunchbox.view.adapter.RestaurantAdapter;
import com.tip.lunchbox.view.listeners.RecyclerTouchListener;
import com.tip.lunchbox.viewmodel.CollectionDetailsViewModel;

public class CollectionDetails extends AppCompatActivity {
    private ActivityCollectionDetailsBinding binding;
    private CollectionDetailsViewModel viewModel;
    private RestaurantAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCollectionDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupRecyclerView();

        viewModel = new ViewModelProvider(this).get(CollectionDetailsViewModel.class);
        int collectionId = getIntent().getIntExtra(Constants.INTENT_COLLECTION_ID, 0);
        String collectionName = getIntent().getStringExtra(Constants.INTENT_COLLECTION_NAME);
        binding.appBarTvLocation.setText(collectionName);

        String cityIdString = SharedPreferencesUtil.getStringPreference(
                this, Constants.PREF_USER_CITY_ID);
        int cityId = Integer.parseInt(cityIdString);
        loadData(collectionId, cityId);

    }

    private void loadData(int collectionId, int cityId) {
        showLoadingView();
        viewModel.getSearchResponseLiveData(collectionId, cityId).observe(this, searchResponse -> {
            if (searchResponse != null
                    && !searchResponse.getRestaurantContainers().isEmpty()) {
                binding.shimmerRecyclerView.setVisibility(View.GONE);
                binding.llErrorOccurred.setVisibility(View.GONE);
                binding.rvCollectionsDetails.setVisibility(View.VISIBLE);
                adapter.setData(searchResponse.getRestaurantContainers());
            } else {
                showErrorView();
            }
        });
    }

    private void showLoadingView() {
        binding.rvCollectionsDetails.setVisibility(View.GONE);
        binding.llErrorOccurred.setVisibility(View.GONE);
        binding.shimmerRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorView() {
        binding.rvCollectionsDetails.setVisibility(View.GONE);
        binding.shimmerRecyclerView.setVisibility(View.GONE);
        binding.llErrorOccurred.setVisibility(View.VISIBLE);
    }

    private void setupRecyclerView() {
        adapter = new RestaurantAdapter(this);
        binding.rvCollectionsDetails.setAdapter(adapter);
        binding.rvCollectionsDetails.setLayoutManager(new LinearLayoutManager(this));

        new RecyclerTouchListener(this, binding.rvCollectionsDetails, (view, position) -> {
            Intent intent = new Intent(this, RestaurantDetails.class);
            intent.putExtra(Constants.INTENT_RES_ID, adapter.getData().get(position)
                    .getRestaurant().getId());
            startActivity(intent);
        });
    }
}