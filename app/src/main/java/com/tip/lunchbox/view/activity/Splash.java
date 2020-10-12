package com.tip.lunchbox.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tip.lunchbox.utilities.Constants;
import com.tip.lunchbox.utilities.SharedPreferencesUtil;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView()
                .setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);

        // to check first time user
        if (SharedPreferencesUtil.getBooleanPreference(this, Constants.PREF_FIRST_TIME, true)) {
            SharedPreferencesUtil.setBooleanPreference(this, Constants.PREF_FIRST_TIME, false);
            startActivity(new Intent(this, SetupActivity.class));
        } else {
            startActivity(new Intent(this, MainActivity.class));
        }
        finish();
    }
}