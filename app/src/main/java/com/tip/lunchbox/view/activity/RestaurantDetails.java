package com.tip.lunchbox.view.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.tip.lunchbox.R;
import com.tip.lunchbox.databinding.ActivityRestaurantDetailsBinding;
import com.tip.lunchbox.model.Restaurant;
import com.tip.lunchbox.utilities.Constants;
import com.tip.lunchbox.view.adapter.HighlightsAdapter;
import com.tip.lunchbox.view.adapter.PhoneNumberAdapter;
import com.tip.lunchbox.view.listeners.RecyclerTouchListener;
import com.tip.lunchbox.viewmodel.RestaurantDetailsViewModel;

import java.util.Arrays;
import java.util.List;

public class RestaurantDetails extends AppCompatActivity {

    private final int callerPermissionCode = 29;
    private RestaurantDetailsViewModel viewModel;
    private ActivityRestaurantDetailsBinding binding;
    private PhoneNumberAdapter phoneNumberAdapter;
    private HighlightsAdapter highlightsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRestaurantDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(RestaurantDetailsViewModel.class);
        String resId = getIntent().getStringExtra(Constants.INTENT_RES_ID);
        assert resId != null;

        // creating adapter instances
        phoneNumberAdapter = new PhoneNumberAdapter(this);
        highlightsAdapter = new HighlightsAdapter(this);
        setupRecyclerViews();
        viewModel.getRestaurantLiveData(Integer.parseInt(resId)).observe(this, this::setData);
    }

    /**
     * This method is used to setup {@link androidx.recyclerview.widget.RecyclerView}
     * used in this activity.
     */
    private void setupRecyclerViews() {
        binding.rvPhoneNumber.setLayoutManager(new LinearLayoutManager(this));
        binding.rvPhoneNumber.setAdapter(phoneNumberAdapter);

        binding.rvHighlights.setLayoutManager(new LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                true));
        binding.rvHighlights.setAdapter(highlightsAdapter);

        // Item click listener for phone number's recyclerview
        new RecyclerTouchListener(this, binding.rvPhoneNumber, (view, position) -> {
            if (checkPermission()) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + phoneNumberAdapter.getData().get(position)));
                if (callIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(callIntent);
                } else {
                    Toast.makeText(
                            this,
                            getString(R.string.dialer_app_not_found),
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean checkPermission() {
        String callPermission = Manifest.permission.CALL_PHONE;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    this, callPermission) == PackageManager.PERMISSION_DENIED) {
                boolean showRationale = shouldShowRequestPermissionRationale(callPermission);
                if (showRationale) {
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE},
                            callerPermissionCode);
                } else {
                    Toast.makeText(this, R.string.permission_rationale_call,
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                return true;
            }
        }
        return false;
    }

    // This method is used to set data into layout elements
    private void setData(Restaurant restaurant) {
        binding.toolbar.setTitle(restaurant.getName());
        binding.tvRating.setText(restaurant.getUserRating().getAggregateRating());
        setOurReviewText(Float.parseFloat(restaurant.getUserRating().getAggregateRating()));

        binding.tvRating.setBackgroundColor(
                Color.parseColor("#" + restaurant.getUserRating().getRatingColor()));
        binding.tvLocation.setText(restaurant.getLocation().getLocalityVerbose());
        Glide.with(this)
                .load(restaurant.getFeaturedImage())
                .centerCrop()
                .into(binding.ivRestaurant);
        binding.chipDirections.setOnClickListener(view -> getDirections(restaurant));
        addPhoneNumbers(restaurant.getPhoneNumbers());
        highlightsAdapter.setData(restaurant.getHighlights());
    }

    private void addPhoneNumbers(String phoneNumbers) {
        List<String> phoneNumbersList = Arrays.asList(phoneNumbers.split(", ", 0));
        phoneNumberAdapter.setData(phoneNumbersList);
    }

    /**
     * This function is used to create an Intent for Google Maps to get the directions to the
     * restaurant.
     *
     * @param restaurant the restaurant whose coordinates are to be passed to the intent
     */
    private void getDirections(Restaurant restaurant) {
        Uri url = Uri.parse(String.format("geo:%s,%s?q=%s",
                restaurant.getLocation().getLatitude(),
                restaurant.getLocation().getLatitude(),
                restaurant.getName()));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        mapIntent.setData(url);
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            Toast.makeText(this, getString(R.string.maps_not_found), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * This method is used to set a hardcoded review text based on the ratings fetched from APIs.
     * @param aggregateRating restaurant's rating fetched from the APIs.
     */
    private void setOurReviewText(float aggregateRating) {
        String ourReview;
        if (aggregateRating >= 4) {
            ourReview = getString(R.string.our_review_good);
        } else if (aggregateRating < 4 && aggregateRating > 2) {
            ourReview = getString(R.string.our_review_average);
        } else {
            ourReview = getString(R.string.our_review_bad);
        }
        binding.tvOurReview.setText(ourReview);
    }
}