package com.tip.lunchbox.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.tip.lunchbox.R;
import com.tip.lunchbox.view.fragment.SetupFragment;

public class SetupActivity extends AppCompatActivity {

    private static final String TAG = "SetupActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);

        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            // Launch SetupFragment after 3 seconds
            SetupFragment setupFragmentModalSheet = SetupFragment.newInstance();
            setupFragmentModalSheet.show(getSupportFragmentManager(), TAG);
        }, 3000);

    }

    public void launchMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}