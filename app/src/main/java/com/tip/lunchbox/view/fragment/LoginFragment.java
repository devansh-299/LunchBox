package com.tip.lunchbox.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.tip.lunchbox.databinding.FragmentLoginBinding;
import com.tip.lunchbox.model.server.request.Login;
import com.tip.lunchbox.utilities.Validator;
import com.tip.lunchbox.view.activity.MainActivity;
import com.tip.lunchbox.viewmodel.LoginFragmentViewModel;

public class LoginFragment extends Fragment implements View.OnClickListener {
    private FragmentLoginBinding binding;
    private LoginFragmentViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        binding.btLogin.setOnClickListener(this);
        viewModel = new ViewModelProvider(this).get(LoginFragmentViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        if (view == binding.btLogin) {
            setEnabled(false);
            String username = binding.etUsername.getText().toString();
            String password = binding.etPassword.getText().toString();
            Boolean unValid = Validator.usernameValidator(username);
            Boolean passValid = Validator.passwordValidator(password);
            if (!unValid) {
                binding.tiUsername.setError("Invalid Username");
                setEnabled(true);
            }
            if (!passValid) {
                binding.tiUsername.setError("Invalid Password");
                setEnabled(true);
            }
            if (unValid && passValid) {
                viewModel.loginUserLiveData(new Login(username, password))
                        .observe(getViewLifecycleOwner(),
                                aBoolean -> {
                                    if (aBoolean != null) {
                                        if (aBoolean) {
                                            startActivity(new Intent(getActivity(),
                                                    MainActivity.class));
                                            getActivity().finish();
                                        } else {
                                            Toast.makeText(getContext(),
                                                    viewModel.getErrorMessage(),
                                                    Toast.LENGTH_LONG).show();
                                            setEnabled(true);
                                        }
                                    }
                                }
                    );
            }
        }
    }

    private void setEnabled(Boolean bool) {
        binding.etPassword.setEnabled(bool);
        binding.etUsername.setEnabled(bool);
        binding.btLogin.setEnabled(bool);
    }
}
