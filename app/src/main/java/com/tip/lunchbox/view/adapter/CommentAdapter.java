package com.tip.lunchbox.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tip.lunchbox.databinding.ItemCommentsBinding;
import com.tip.lunchbox.model.server.response.CommentsResponse;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentsViewHolder> {
    Context context;
    List<CommentsResponse> commentsResponses;

    public CommentAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<CommentsResponse> commentsResponses) {
        this.commentsResponses = commentsResponses;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CommentAdapter.CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                int viewType) {
        return new CommentsViewHolder(
                ItemCommentsBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.CommentsViewHolder holder, int position) {
        holder.setData(commentsResponses.get(position));
    }

    @Override
    public int getItemCount() {
        return commentsResponses == null ? 0 : commentsResponses.size();
    }

    public class CommentsViewHolder extends RecyclerView.ViewHolder {
        private ItemCommentsBinding commentsBinding;

        public CommentsViewHolder(@NonNull ItemCommentsBinding binding) {
            super(binding.getRoot());
            commentsBinding = binding;
        }

        public void setData(CommentsResponse commentsResponse) {
            commentsBinding.tvCommentAuthor.setText(commentsResponse.getAuthor());
            commentsBinding.tvCommentBody.setText(commentsResponse.getComment());
            commentsBinding.rbCommentRating.setRating(commentsResponse.getRating());
            commentsBinding.ratingNumber.setText(String.valueOf(
                    commentsResponse.getRating().floatValue()));
        }
    }
}
