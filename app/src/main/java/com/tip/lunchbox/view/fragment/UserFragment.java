package com.tip.lunchbox.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.common.util.SharedPreferencesUtils;
import com.tip.lunchbox.databinding.FragmentUserBinding;
import com.tip.lunchbox.model.server.response.CommentsResponseContainer;
import com.tip.lunchbox.utilities.Constants;
import com.tip.lunchbox.utilities.SharedPreferencesUtil;
import com.tip.lunchbox.view.activity.SetupActivity;
import com.tip.lunchbox.view.adapter.CommentAdapter;
import com.tip.lunchbox.viewmodel.UserFragmentViewModel;

import org.jetbrains.annotations.NotNull;

public class UserFragment extends Fragment implements View.OnClickListener {
    FragmentUserBinding binding;
    CommentAdapter commentAdapter;
    UserFragmentViewModel viewModel;


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUserBinding.inflate(inflater, container, false);
        commentAdapter = new CommentAdapter(requireContext());
        viewModel = new ViewModelProvider(this).get(UserFragmentViewModel.class);
        binding.rvUserComments.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvUserComments.setAdapter(commentAdapter);
        binding.btLogout.setOnClickListener(this);
        // TODO get current username
        viewModel.getCommentsResponseLiveData("gun")
                .observe(getViewLifecycleOwner(), this::setData);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void setData(CommentsResponseContainer container) {
        commentAdapter.setData(container.getComments());
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
