package com.tip.lunchbox.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tip.lunchbox.databinding.ItemHighlightBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HighlightsAdapter extends
        RecyclerView.Adapter<HighlightsAdapter.HighLightViewHolder> {

    Context context;
    List<String> highLightList;

    public HighlightsAdapter(Context context) {
        this.context = context;
        this.highLightList = new ArrayList<>();
    }

    public void setData(List<String> highLightList) {
        this.highLightList = highLightList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HighLightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HighLightViewHolder(ItemHighlightBinding
                .inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HighLightViewHolder holder, int position) {
        holder.addData(highLightList.get(position));
    }

    @Override
    public int getItemCount() {
        return highLightList == null ? 0 : highLightList.size();
    }

    public List<String> getData() {
        return highLightList;
    }

    public static class HighLightViewHolder extends RecyclerView.ViewHolder {

        private ItemHighlightBinding highlightBinding;

        public HighLightViewHolder(
                @NotNull ItemHighlightBinding binding) {
            super(binding.getRoot());
            highlightBinding = binding;
        }

        public void addData(String phoneNumber) {
            highlightBinding.itemTvHighlight.setText(phoneNumber);
        }
    }
}



