package com.tip.lunchbox.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Toast;

import com.tip.lunchbox.R;
import com.tip.lunchbox.utilities.Constants;
import com.tip.lunchbox.utilities.SharedPreferencesUtil;
import com.tip.lunchbox.view.fragment.LoginFragment;
import com.tip.lunchbox.view.fragment.SignUpFragment;

public class SetupActivity extends AppCompatActivity {

    private static final String TAG = "SetupActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        String logoutMessage = getIntent().getStringExtra("message");
        if (logoutMessage != null) {
            Toast.makeText(SetupActivity.this, logoutMessage, Toast.LENGTH_SHORT).show();
        }
        
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            // Condition can also be set on refresh token expiry.
            if (SharedPreferencesUtil
                    .getBooleanPreference(this, Constants.PREF_FIRST_TIME, true)) {
                launchSignUpFragment();
            } else if (TextUtils.isEmpty(SharedPreferencesUtil
                    .getStringPreference(this, Constants.PREF_AUTH_TOKEN))) {
                launchLoginFragment();
            } else {
                launchMainActivity();
            }

        }, 2000);
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