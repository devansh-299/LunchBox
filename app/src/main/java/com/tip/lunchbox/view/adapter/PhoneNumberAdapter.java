package com.tip.lunchbox.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tip.lunchbox.databinding.ItemPhoneNumberBinding;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PhoneNumberAdapter extends
        RecyclerView.Adapter<PhoneNumberAdapter.PhoneNumberViewHolder> {

    Context context;
    List<String> phoneNumberList;

    public PhoneNumberAdapter(Context context) {
        this.context = context;
        this.phoneNumberList = new ArrayList<>();
    }

    public void setData(List<String> phoneNumberList) {
        this.phoneNumberList = phoneNumberList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhoneNumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PhoneNumberViewHolder(ItemPhoneNumberBinding
                .inflate(LayoutInflater.from(context), parent, false), context);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneNumberViewHolder holder, int position) {
        holder.addData(phoneNumberList.get(position));
    }

    @Override
    public int getItemCount() {
        return phoneNumberList == null ? 0 : phoneNumberList.size();
    }

    public List<String> getData() {
        return phoneNumberList;
    }

    public static class PhoneNumberViewHolder extends RecyclerView.ViewHolder {

        private ItemPhoneNumberBinding phoneNumberBinding;
        Context context;

        public PhoneNumberViewHolder(
                @NotNull ItemPhoneNumberBinding binding,
                Context context) {
            super(binding.getRoot());
            phoneNumberBinding = binding;
            this.context = context;
        }

        public void addData(String phoneNumber) {
            phoneNumberBinding.itemTvPhoneNumber.setText(phoneNumber);
        }
    }
}



