package com.tip.lunchbox.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tip.lunchbox.databinding.ViewholderRestaurantBinding;
import com.tip.lunchbox.model.Restaurant;
import com.tip.lunchbox.model.RestaurantContainer;

import java.util.List;

public class RVRestaurantAdapter extends RecyclerView.Adapter<RVRestaurantAdapter.RestaurantViewHolder> {
    Context context;
    List<RestaurantContainer> restaurantsList;


    public RVRestaurantAdapter(Context context , List<RestaurantContainer> restaurantContainers) {
        this.context = context;
        this.restaurantsList = restaurantContainers;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new RestaurantViewHolder( ViewholderRestaurantBinding
                .inflate(LayoutInflater.from(context) , parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {

        holder.adddata(restaurantsList.get(position));

    }

    @Override
    public int getItemCount() {

       return restaurantsList==null?0:restaurantsList.size();

    }

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {

        private ViewholderRestaurantBinding viewholderbinding;

        public RestaurantViewHolder(@NonNull ViewholderRestaurantBinding binding) {
            super(binding.getRoot());
            viewholderbinding = binding;
        }

        public void adddata(RestaurantContainer restaurantContainer){
            Restaurant restaurant = restaurantContainer.getRestaurant();
            viewholderbinding.vhrestaurantname.setText(restaurant.getName());
            Glide.with(viewholderbinding.getRoot())
                    .load(restaurant.getThumb())
                    .into(viewholderbinding.vhrestaurantimageView);
            viewholderbinding.vhrating.setText(
                    restaurant.getUserRating().getAggregateRating()
            );
            viewholderbinding.vhrating.setBackgroundColor(
                    Color.parseColor("#"+restaurant.getUserRating().getRatingColor())
            );

            viewholderbinding.vhlocalityshort.setText(
                    restaurant.getLocation().getLocalityVerbose()
            );

            try{
                viewholderbinding.vhhighlights.setText(restaurant.getHighlights().get(0));
            }
            catch (ArrayIndexOutOfBoundsException e) {
            }

        }
    }
}


