package com.tip.lunchbox.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.tip.lunchbox.databinding.ActivityRestaurantDetailsBinding;
import com.tip.lunchbox.model.Restaurant;
import com.tip.lunchbox.utilities.Constants;
import com.tip.lunchbox.viewmodel.RestaurantDetailsViewModel;

import java.util.Arrays;
import java.util.List;

public class RestaurantDetails extends AppCompatActivity {
    RestaurantDetailsViewModel viewModel;
    ActivityRestaurantDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRestaurantDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(RestaurantDetailsViewModel.class);
        String resId = getIntent().getStringExtra(Constants.INTENT_RES_ID);

        assert resId != null;
        viewModel.getRestaurantLiveData(Integer.parseInt(resId)).observe(this, this::setData);
    }


    private void setData(Restaurant restaurant) {
        binding.tvResName.setText(restaurant.getName());
        binding.tvResRating.setText(restaurant.getUserRating().getAggregateRating());
        binding.tvResRating.setBackgroundColor(
                Color.parseColor("#"
                        + restaurant.getUserRating().getRatingColor()));
        Glide.with(this).load(restaurant.getThumb()).into(binding.imgViewRes);
        ArrayAdapter<String> cuisineAdapter =
                listToArrayAdapter(Arrays.asList(restaurant.getCuisines().split(",")));

        binding.lvCuisine.setAdapter(listToArrayAdapter(restaurant.getHighlights()));
        binding.lvHighlights.setAdapter(cuisineAdapter);
    }

    private ArrayAdapter<String> listToArrayAdapter(List<String> list) {
        return new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
    }
}