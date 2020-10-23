package com.tip.lunchbox.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.tip.lunchbox.R;
import com.tip.lunchbox.databinding.ActivityMenuBinding;
import com.tip.lunchbox.utilities.Constants;

@SuppressLint("SetJavaScriptEnabled")
public class MenuActivity extends AppCompatActivity {

    ActivityMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String restaurantName = getIntent().getStringExtra(Constants.INTENT_RESTAURANT_NAME);
        String menuUrl = getIntent().getStringExtra(Constants.INTENT_MENU_URL);
        binding.bsWebView.getSettings().setJavaScriptEnabled(true);

        if (restaurantName != null && menuUrl != null) {
            binding.bsTvRestaurantName.setText(restaurantName);
            binding.bsWebView.loadUrl(menuUrl);
        } else {
            Toast.makeText(this, getString(R.string.error_occurred), Toast.LENGTH_SHORT).show();
            finish();
        }
        binding.bsIbExit.setOnClickListener(view -> finish());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}