package com.tip.lunchbox.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.tip.lunchbox.databinding.FragmentLoginBinding;
import com.tip.lunchbox.model.server.request.Login;
import com.tip.lunchbox.utilities.Validator;
import com.tip.lunchbox.view.activity.SetupActivity;
import com.tip.lunchbox.viewmodel.LoginFragmentViewModel;

public class LoginFragment extends BottomSheetDialogFragment implements View.OnClickListener {
    private FragmentLoginBinding binding;
    private LoginFragmentViewModel viewModel;

    public static LoginFragment newInstance() {
        LoginFragment setupBottomSheet = new LoginFragment();
        setupBottomSheet.setCancelable(false);
        return setupBottomSheet;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        binding.btLogin.setOnClickListener(this);
        binding.tvSignUp.setOnClickListener(this);
        viewModel = new ViewModelProvider(this).get(LoginFragmentViewModel.class);

        viewModel.loginUserLiveData()
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
        if (view == binding.btLogin) {
            setEnabled(false);
            String username = binding.etUsername.getText().toString().trim();
            String password = binding.etPassword.getText().toString();
            Boolean unValid = Validator.usernameValidator(username);
            Boolean passValid = Validator.passwordValidator(password);
            if (!unValid) {
                binding.tiUsername.setError("Invalid Username");
                setEnabled(true);
            }
            if (!passValid) {
                binding.tiPassword.setError("Invalid Password");
                setEnabled(true);
            }
            if (unValid && passValid) {
                viewModel.loginUser(new Login(username, password));
            }
        }
        if (view == binding.tvSignUp) {
            ((SetupActivity) getActivity()).launchSignUpFragment();
        }
    }

    private void setEnabled(Boolean bool) {
        binding.etPassword.setEnabled(bool);
        binding.etUsername.setEnabled(bool);
        binding.btLogin.setEnabled(bool);
    }
}
