package com.tip.lunchbox.view.fragment;

import android.os.Bundle;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentActivity;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.tip.lunchbox.R;
import com.tip.lunchbox.databinding.FragmentSetupBinding;
import com.tip.lunchbox.utilities.Constants;
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
        binding.btnProceed.setOnClickListener(this);
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
        String city = binding.etCity.getText().toString();
        String mobileNumber = binding.etMobileNumber.getText().toString();

        if (isValid(userName, city, mobileNumber)) {
            if (getActivity() != null) {
                saveDetails(getActivity(), userName, city, mobileNumber);
                ((SetupActivity)getActivity()).launchMainActivity();
                dismiss();
            }
        }
    }

    private boolean isValid(String userName, String city, String mobileNumber) {
        boolean isValid = true;
        if (TextUtils.isEmpty(userName)) {
            binding.etUserName.setError(getString(R.string.cannot_leave_blank));
            isValid = false;
        }
        if (TextUtils.isEmpty(city)) {
            binding.etCity.setError(getString(R.string.cannot_leave_blank));
            isValid = false;
        }
        if (TextUtils.isEmpty(mobileNumber)) {
            binding.etMobileNumber.setError(getString(R.string.cannot_leave_blank));
            isValid = false;
        }
        return isValid;
    }

    private void saveDetails(FragmentActivity activity,
                             String userName,
                             String city,
                             String mobileNumber) {
        SharedPreferencesUtil.setStringPreference(activity, Constants.PREF_USER_NAME, userName);
        SharedPreferencesUtil.setStringPreference(activity, Constants.PREF_CITY, city);
        SharedPreferencesUtil.setStringPreference(
                activity, Constants.PREF_MOBILE_NUMBER, mobileNumber);
    }

}