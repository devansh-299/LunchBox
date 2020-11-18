package com.tip.lunchbox.view.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.tip.lunchbox.R;
import com.tip.lunchbox.databinding.ActivityRestaurantDetailsBinding;
import com.tip.lunchbox.databinding.DialogAddReviewBinding;
import com.tip.lunchbox.model.server.request.Comment;
import com.tip.lunchbox.model.server.response.CommentsResponse;
import com.tip.lunchbox.model.server.response.CustomResponse;
import com.tip.lunchbox.model.zomato.Restaurant;
import com.tip.lunchbox.utilities.Constants;
import com.tip.lunchbox.utilities.PermissionHelper;
import com.tip.lunchbox.view.adapter.CommentAdapter;
import com.tip.lunchbox.view.adapter.HighlightsAdapter;
import com.tip.lunchbox.view.adapter.PhoneNumberAdapter;
import com.tip.lunchbox.view.listeners.RecyclerTouchListener;
import com.tip.lunchbox.viewmodel.RestaurantDetailsViewModel;

import java.util.Arrays;
import java.util.List;

public class RestaurantDetails extends AppCompatActivity implements View.OnClickListener {

    private final int callerPermissionCode = 29;
    private RestaurantDetailsViewModel viewModel;
    private ActivityRestaurantDetailsBinding binding;
    private DialogAddReviewBinding addReviewBinding;
    private PhoneNumberAdapter phoneNumberAdapter;
    private HighlightsAdapter highlightsAdapter;
    private CommentAdapter commentAdapter;
    private String resId;
    private AlertDialog newReviewDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRestaurantDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(RestaurantDetailsViewModel.class);
        resId = getIntent().getStringExtra(Constants.INTENT_RES_ID);
        assert resId != null;
        binding.addReviewButton.setOnClickListener(this);
        // creating adapter instances
        phoneNumberAdapter = new PhoneNumberAdapter(this);
        highlightsAdapter = new HighlightsAdapter(this);
        commentAdapter = new CommentAdapter(this);
        setupRecyclerViews();

        binding.appBar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if (Math.abs(verticalOffset) - appBarLayout.getTotalScrollRange() == 0) {
                // Collapsed State
                binding.fabMenu.show();
                binding.toolbar.setBackgroundColor(getColor(R.color.white));
                binding.toolbar.setTitleTextColor(getColor(R.color.colorPrimary));

            } else {
                // Expanded State
                binding.fabMenu.hide();
                binding.toolbar.setBackgroundColor(getColor(R.color.colorTransparent));
            }
        });
        viewModel.getRestaurantLiveData(Integer.parseInt(resId)).observe(this, this::setData);
        viewModel.getCommentsResponseLiveData(resId).observe(this, commentsResponses ->
                commentAdapter.setData(commentsResponses.getComments()));
        viewModel.postCommentLiveData().observe(this, aBoolean -> {
            if (aBoolean != null) {
                if (aBoolean) {
                    Toast.makeText(this, "Comment Posted", Toast.LENGTH_SHORT).show();
                    viewModel.getComments(resId);
                    newReviewDialog.dismiss();
                } else {
                    Toast.makeText(this,
                            viewModel.getErrorMessage(),
                            Toast.LENGTH_SHORT).show();
                    addReviewBinding.tiComment.setEnabled(true);
                    addReviewBinding.rbRes.setEnabled(true);
                }
            }
        });
    }

    /**
     * This method is used to setup {@link androidx.recyclerview.widget.RecyclerView}
     * used in this activity.
     */
    private void setupRecyclerViews() {
        binding.rvPhoneNumber.setLayoutManager(new LinearLayoutManager(this));
        binding.rvPhoneNumber.setAdapter(phoneNumberAdapter);
        binding.rvComments.setAdapter(commentAdapter);
        binding.rvHighlights.setLayoutManager(new LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false));
        binding.rvHighlights.setAdapter(highlightsAdapter);
        binding.rvComments.setLayoutManager(new LinearLayoutManager(this));
        String callPermission = Manifest.permission.CALL_PHONE;
        // Item click listener for phone number's recyclerview
        new RecyclerTouchListener(this, binding.rvPhoneNumber, (view, position) -> {
            if (PermissionHelper.checkPermission(this, callPermission)) {
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
            } else {
                PermissionHelper.requestPermission(this, callPermission, callerPermissionCode);
            }
        });
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

        // onClickListener for opening up the MenuActivity
        binding.fabMenu.setOnClickListener(
                view -> {
                    Intent menuIntent = new Intent(this, MenuActivity.class);
                    menuIntent.putExtra(Constants.INTENT_RESTAURANT_NAME, restaurant.getName());
                    menuIntent.putExtra(Constants.INTENT_MENU_URL, restaurant.getMenuUrl());
                    startActivity(menuIntent);
                }
        );
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
     *
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

    @Override
    public void onClick(View view) {
        if (view == binding.addReviewButton) {
            addReviewBinding = DialogAddReviewBinding.inflate(LayoutInflater
                    .from(RestaurantDetails.this));
            newReviewDialog = new MaterialAlertDialogBuilder(this)
                    .setView(addReviewBinding.getRoot())
                    .setPositiveButton("Post Review", (dialog, which) -> {
                        Comment newComment = new Comment(
                                addReviewBinding.tiComment.getText().toString().trim(),
                                (int) addReviewBinding.rbRes.getRating(), resId);
                        viewModel.postComment(newComment);
                        addReviewBinding.tiComment.setEnabled(false);
                        addReviewBinding.rbRes.setEnabled(false);
                    })
                    .setCancelable(true)
                    .show();
        }
    }
}