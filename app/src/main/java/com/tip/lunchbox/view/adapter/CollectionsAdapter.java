package com.tip.lunchbox.view.adapter;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.content.Context;
import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.tip.lunchbox.databinding.ItemCollectionsBinding;
import com.tip.lunchbox.model.Collection;
import com.tip.lunchbox.model.CollectionsContainer;
import com.tip.lunchbox.model.RestaurantContainer;

import java.util.List;

public class CollectionsAdapter extends RecyclerView.Adapter<CollectionsAdapter.CollectionsViewHolder> {
    Context context;
    List<CollectionsContainer> collectionsContainers;


    public CollectionsAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<CollectionsContainer> collectionsContainers) {
        this.collectionsContainers = collectionsContainers;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CollectionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new CollectionsViewHolder(ItemCollectionsBinding.inflate(LayoutInflater.from(context),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionsViewHolder holder, int position) {

    holder.adddata(collectionsContainers.get(position));
    }

    @Override
    public int getItemCount() {
        return collectionsContainers == null? 0 : collectionsContainers.size();
    }
    public static class CollectionsViewHolder extends RecyclerView.ViewHolder {
         ItemCollectionsBinding binding;
        public CollectionsViewHolder (@NonNull  ItemCollectionsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }

        public void adddata(CollectionsContainer collectionsContainer) {

            binding.tvcollections.setText(collectionsContainer.getCollection().getTitle());

            Glide.with(binding.getRoot()).load(collectionsContainer.getCollection().getImageUrl())
                    .into(new CustomTarget<Drawable>() {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            binding.vhbgcollections.setBackground(resource);
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {
                            binding.tvcollections.setBackground(placeholder);
                        }
                    });

        }
    }
}
