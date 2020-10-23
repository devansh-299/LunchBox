package com.tip.lunchbox.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.tip.lunchbox.databinding.ActivityCollectionDetailsBinding;
import com.tip.lunchbox.utilities.Constants;
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
        getSupportActionBar().setTitle(collectionName);
        viewModel.getSearchResponseLiveData(collectionId).observe(this, searchResponse ->
                adapter.setData(searchResponse.getRestaurantContainers()));
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