package com.tip.lunchbox.view.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.tip.lunchbox.databinding.ItemCollectionsBinding;
import com.tip.lunchbox.model.CollectionsContainer;

import java.util.List;

public class CollectionsAdapter extends
        RecyclerView.Adapter<CollectionsAdapter.CollectionsViewHolder> {

    Context context;
    List<CollectionsContainer> collectionsContainers;

    public CollectionsAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<CollectionsContainer> collectionsContainers) {
        this.collectionsContainers = collectionsContainers;
        notifyDataSetChanged();
    }

    public List<CollectionsContainer> getData() {
        return collectionsContainers;
    }

    @NonNull
    @Override
    public CollectionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CollectionsViewHolder(
                ItemCollectionsBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionsViewHolder holder, int position) {
        holder.addData(collectionsContainers.get(position));
    }

    @Override
    public int getItemCount() {
        return collectionsContainers == null ? 0 : collectionsContainers.size();
    }

    public static class CollectionsViewHolder extends RecyclerView.ViewHolder {
        ItemCollectionsBinding binding;

        public CollectionsViewHolder(@NonNull ItemCollectionsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void addData(CollectionsContainer collectionsContainer) {

            binding.tvCollections.setText(collectionsContainer.getCollection().getTitle());

            Glide.with(binding.getRoot())
                    .load(collectionsContainer.getCollection().getImageUrl())
                    .transform(new CenterCrop(), new RoundedCorners(20))
                    .into(new CustomTarget<Drawable>() {
                        @Override
                        public void onResourceReady(
                                @NonNull Drawable resource,
                                @Nullable Transition<? super Drawable> transition) {
                            binding.ivCollection.setBackground(resource);
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {
                            binding.ivCollection.setBackground(placeholder);
                        }
                    });
        }
    }
}
