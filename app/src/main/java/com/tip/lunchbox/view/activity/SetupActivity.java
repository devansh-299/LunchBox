package com.tip.lunchbox.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.tip.lunchbox.R;
import com.tip.lunchbox.view.fragment.LoginFragment;
import com.tip.lunchbox.view.fragment.SetupFragment;
import com.tip.lunchbox.view.fragment.SignUpFragment;

public class SetupActivity extends AppCompatActivity {

    private static final String TAG = "SetupActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            // Launch SetupFragment after 4 seconds
            launchLoginFragment();
        }, 4000);
    }

    public void launchMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void launchLoginFragment() {
        LoginFragment setupFragmentModalSheet = LoginFragment.newInstance();
        setupFragmentModalSheet.show(getSupportFragmentManager(), TAG);
    }

    public void launchSignUpFragment() {
        SignUpFragment setupFragmentModalSheet = SignUpFragment.newInstance();
        setupFragmentModalSheet.show(getSupportFragmentManager(), TAG);
    }
}