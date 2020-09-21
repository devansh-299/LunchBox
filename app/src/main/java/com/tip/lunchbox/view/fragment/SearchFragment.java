package com.tip.lunchbox.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;

import com.tip.lunchbox.databinding.FragmentSearchBinding;
import com.tip.lunchbox.view.adapter.CollectionsAdapter;
import com.tip.lunchbox.viewmodel.SearchFramentViewModel;

public class SearchFragment extends Fragment {

    FragmentSearchBinding binding;
    CollectionsAdapter adapter;
    SearchFramentViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentSearchBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(SearchFramentViewModel.class);
        setUpRecyclerView();
        // Observing data
        viewModel.getCollectionsLiveData().observe(
                getViewLifecycleOwner(),
                collectionsResponse -> adapter.setData(
                        collectionsResponse.getCollectionsContainer()));

        return binding.getRoot();
    }

    public void setUpRecyclerView() {

        adapter = new CollectionsAdapter(requireActivity());
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        binding.rvCollections.setLayoutManager(
                new LinearLayoutManager(
                        getActivity(),
                        LinearLayoutManager.HORIZONTAL, false));
        binding.rvCollections.setAdapter(adapter);
        pagerSnapHelper.attachToRecyclerView(binding.rvCollections);

    }

}