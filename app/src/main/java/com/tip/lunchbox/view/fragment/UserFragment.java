package com.tip.lunchbox.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.gms.common.util.SharedPreferencesUtils;
import com.tip.lunchbox.databinding.FragmentUserBinding;
import com.tip.lunchbox.utilities.Constants;
import com.tip.lunchbox.utilities.SharedPreferencesUtil;
import com.tip.lunchbox.view.activity.SetupActivity;

public class UserFragment extends Fragment implements View.OnClickListener {
    FragmentUserBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUserBinding.inflate(inflater, container, false);
        binding.btLogout.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        if (view == binding.btLogout) {
            SharedPreferencesUtil
                    .setStringPreference(requireContext(), Constants.PREF_AUTH_TOKEN, "");
            SharedPreferencesUtil
                    .setStringPreference(requireContext(), Constants.PREF_REFRESH_TOKEN, "");
            Intent intent = new Intent(requireActivity(), SetupActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            requireActivity().startActivity(intent);
            requireActivity().finish();
        }
    }
}
