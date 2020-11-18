package com.tip.lunchbox.view.fragment;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.tip.lunchbox.databinding.FragmentSignUpBinding;
import com.tip.lunchbox.model.server.request.SignUp;
import com.tip.lunchbox.utilities.Constants;
import com.tip.lunchbox.utilities.SharedPreferencesUtil;
import com.tip.lunchbox.utilities.Validator;
import com.tip.lunchbox.view.activity.SetupActivity;
import com.tip.lunchbox.viewmodel.SignUpFragmentViewModel;

public class SignUpFragment extends BottomSheetDialogFragment implements View.OnClickListener {
    private FragmentSignUpBinding binding;
    private SignUpFragmentViewModel viewModel;

    public static SignUpFragment newInstance() {
        SignUpFragment setupBottomSheet = new SignUpFragment();
        setupBottomSheet.setCancelable(false);
        return setupBottomSheet;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSignUpBinding.inflate(inflater, container, false);
        binding.btSignUp.setOnClickListener(this);
        binding.tvLogin.setOnClickListener(this);
        viewModel = new ViewModelProvider(this).get(SignUpFragmentViewModel.class);

        viewModel.signUpUserLiveData()
                .observe(getViewLifecycleOwner(),
                        aBoolean -> {
                            if (aBoolean != null) {
                                if (aBoolean) {
                                    ((SetupActivity) getActivity()).launchMainActivity();
                                } else {
                                    Toast.makeText(getContext(),
                                            viewModel.getErrorMessage(),
                                            Toast.LENGTH_SHORT).show();
                                    setEnabled(true);
                                }
                            }
                        }
            );

        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        if (view == binding.btSignUp) {
            setEnabled(false);
            String username = binding.etUsername.getText().toString().trim();
            String password = binding.etPassword.getText().toString();
            String rePassword = binding.etConfirmPassword.getText().toString();
            Long phone = Long.valueOf(binding.etPhone.getText().toString());
            validateUserSignUp(username, password, rePassword, phone);
        }

        if (view == binding.tvLogin) {
            this.dismissAllowingStateLoss();
            SharedPreferencesUtil
                    .setBooleanPreference(requireContext(), Constants.PREF_FIRST_TIME,false);
            ((SetupActivity) getActivity()).launchLoginFragment();
        }
    }

    private void setEnabled(Boolean bool) {
        binding.etPassword.setEnabled(bool);
        binding.etUsername.setEnabled(bool);
        binding.etConfirmPassword.setEnabled(bool);
        binding.etPhone.setEnabled(bool);
        binding.btSignUp.setEnabled(bool);
    }

    private void validateUserSignUp(String username, String password,
                                    String rePassword, Long phone) {
        boolean unValid = Validator.usernameValidator(username);
        boolean passValid = Validator.passwordValidator(password);
        boolean valid = true;
        dismissError();
        if (!unValid) {
            binding.tiUsername.setError("Invalid Username");
            setEnabled(true);
            valid = false;
        }
        if (!passValid) {
            binding.tiPassword.setError("Invalid Password");
            setEnabled(true);
            valid = false;
        }
        if (!password.equals(rePassword)) {
            binding.tiConfirmPassword.setError("Passwords do not Match");
            binding.tiConfirmPassword.setError("Passwords do not Match");
            setEnabled(true);
            valid = false;
        }
        // TODO add better phone number validation (this one is only for searching)
        if (!Patterns.PHONE.matcher(String.valueOf(phone)).find()) {
            binding.tiPhone.setError("Invalid Phone Number");
            setEnabled(true);
            valid = false;
        }
        if (valid) {
            viewModel.signUpUser(new SignUp(username, password, phone));
        }
    }

    private void dismissError() {
        binding.tiPhone.setError(null);
        binding.tiConfirmPassword.setError(null);
        binding.tiPassword.setError(null);
        binding.tiUsername.setError(null);
    }

}

