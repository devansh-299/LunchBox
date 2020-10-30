package com.tip.lunchbox.view.fragment;

import android.Manifest;
import android.os.Bundle;
import android.text.TextUtils;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.tip.lunchbox.R;
import com.tip.lunchbox.databinding.FragmentSetupBinding;
import com.tip.lunchbox.utilities.Constants;
import com.tip.lunchbox.utilities.LocationHelper;
import com.tip.lunchbox.utilities.PermissionHelper;
import com.tip.lunchbox.utilities.SharedPreferencesUtil;
import com.tip.lunchbox.view.activity.SetupActivity;

import org.jetbrains.annotations.NotNull;

public class SetupFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    FragmentSetupBinding binding;

    public static SetupFragment newInstance() {
        SetupFragment setupBottomSheet = new SetupFragment();
        setupBottomSheet.setCancelable(false);
        return setupBottomSheet;
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSetupBinding.inflate(inflater, container, false);
        binding.chipProceed.setOnClickListener(this);
        binding.cbLocation.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked && getActivity() != null) {
                boolean success = LocationHelper.getLocation(getActivity());
                if (!success) {
                    binding.cbLocation.setChecked(false);
                }
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public void onClick(View view) {
        String userName = binding.etUserName.getText().toString();
        String emailId = binding.etEmail.getText().toString();
        String mobileNumber = binding.etMobileNumber.getText().toString();

        if (isValid(userName, emailId, mobileNumber)) {
            if (getActivity() != null
                    && PermissionHelper.checkPermission(
                            getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
                saveDetails(getActivity(), userName, emailId, mobileNumber);
                ((SetupActivity)getActivity()).launchMainActivity();
                dismiss();
            } else {
                Toast.makeText(getActivity(), R.string.cannot_proceed_permission,
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isValid(String userName, String email, String mobileNumber) {
        boolean isValid = true;
        if (TextUtils.isEmpty(userName)) {
            binding.etUserName.setError(getString(R.string.cannot_leave_blank));
            isValid = false;
        }
        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etEmail.setError(getString(R.string.invalid_email));
            isValid = false;
        }
        if (TextUtils.isEmpty(mobileNumber) || !Patterns.PHONE.matcher(mobileNumber).matches()) {
            binding.etMobileNumber.setError(getString(R.string.invalid_phone));
            isValid = false;
        }
        if (!binding.cbLocation.isChecked()) {
            isValid = false;
        }
        return isValid;
    }

    private void saveDetails(FragmentActivity activity,
                             String userName,
                             String city,
                             String mobileNumber) {
        SharedPreferencesUtil.setStringPreference(activity, Constants.PREF_USER_NAME, userName);
        SharedPreferencesUtil.setStringPreference(activity, Constants.PREF_EMAIL, city);
        SharedPreferencesUtil.setStringPreference(
                activity, Constants.PREF_MOBILE_NUMBER, mobileNumber);
        SharedPreferencesUtil.setBooleanPreference(activity, Constants.PREF_FIRST_TIME, false);
    }

}