package com.tip.lunchbox.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.tip.lunchbox.R;
import com.tip.lunchbox.databinding.ItemRestaurantBinding;
import com.tip.lunchbox.model.Restaurant;
import com.tip.lunchbox.model.RestaurantContainer;

import java.util.ArrayList;
import java.util.List;

public class RestaurantAdapter extends
        RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    Context context;
    List<RestaurantContainer> restaurantsList;

    public RestaurantAdapter(Context context) {
        this.context = context;
        this.restaurantsList = new ArrayList<>();
    }

    public void setData(List<RestaurantContainer> restaurantsList) {
        this.restaurantsList = restaurantsList;
        notifyDataSetChanged();
    }

    public List<RestaurantContainer> getData() {
        return restaurantsList;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RestaurantViewHolder(ItemRestaurantBinding
                .inflate(LayoutInflater.from(context), parent, false), context);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        holder.addData(restaurantsList.get(position));
    }

    @Override
    public int getItemCount() {
        return restaurantsList == null ? 0 : restaurantsList.size();
    }

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {

        private ItemRestaurantBinding restaurantItemBinding;
        Context context;
        RequestOptions options;

        public RestaurantViewHolder(@NonNull ItemRestaurantBinding binding, Context context) {
            super(binding.getRoot());
            restaurantItemBinding = binding;
            this.context = context;
            options = new RequestOptions()
                    .centerCrop()
                    .error(R.drawable.app_logo)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .priority(Priority.IMMEDIATE);
        }

        public void addData(RestaurantContainer restaurantContainer) {
            restaurantItemBinding.itemTvHighlights.setVisibility(View.VISIBLE);

            Restaurant restaurant = restaurantContainer.getRestaurant();
            restaurantItemBinding.itemTvRestaurantName.setText(restaurant.getName());
            Glide.with(restaurantItemBinding.getRoot())
                    .load(restaurant.getThumb())
                    .apply(options)
                    .into(restaurantItemBinding.itemIvRestaurant);
            restaurantItemBinding.itemTvRating.setText(
                    restaurant.getUserRating().getAggregateRating());
            restaurantItemBinding.itemTvRating.setBackgroundColor(
                    Color.parseColor("#" + restaurant.getUserRating().getRatingColor()));
            restaurantItemBinding.itemTvLocality.setText(
                    restaurant.getLocation().getLocalityVerbose());

            try {
                String cuisines = restaurant.getCuisines();
                int last = restaurant.getCuisines().indexOf(',');
                cuisines = last != -1 ? cuisines.substring(0, last) : cuisines;
                restaurantItemBinding.itemTvHighlights.setText(
                        context.getString(R.string.placeholder_cuisines, cuisines));
            } catch (NullPointerException error) {
                restaurantItemBinding.itemTvHighlights.setVisibility(View.GONE);
            }
        }
    }
}



