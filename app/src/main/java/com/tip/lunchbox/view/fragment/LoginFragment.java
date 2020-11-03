package com.tip.lunchbox.view.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tip.lunchbox.databinding.FragmentLoginBinding;
import com.tip.lunchbox.utilities.Validator;

public class LoginFragment extends Fragment implements View.OnClickListener {
    FragmentLoginBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        if (view == binding.btLogin) {
            setEnabled(false);
            Boolean unValid = Validator.usernameValidator(binding
                    .etUsername.getText().toString());
            Boolean passValid = Validator.passwordValidator(binding
                    .etPassword.getText().toString());
            if (!unValid) {
                binding.tiUsername.setError("Invalid Username");
                setEnabled(true);
            }
            if (!passValid) {
                binding.tiUsername.setError("Invalid Password");
                setEnabled(true);
            }
            if (unValid && passValid) {
                //Send Req to backend here
            }
        }
    }

    private void setEnabled(Boolean bool) {
        binding.etPassword.setEnabled(bool);
        binding.etUsername.setEnabled(bool);
        binding.btLogin.setEnabled(bool);
    }
}
