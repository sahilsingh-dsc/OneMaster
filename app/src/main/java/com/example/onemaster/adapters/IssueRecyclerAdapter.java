package com.example.onemaster.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onemaster.R;
import com.example.onemaster.holders.IssueViewHolder;
import com.example.onemaster.models.IssueModel;
import com.example.onemaster.utils.AsyncListDiffer;

import java.util.List;
import java.util.Objects;

public class IssueRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private AsyncListDiffer<IssueModel> differ;

    public IssueRecyclerAdapter(Context context) {
        this.context = context;
        this.differ = new AsyncListDiffer<>(this, new DiffUtil.ItemCallback<IssueModel>() {
            @Override
            public boolean areItemsTheSame(@NonNull IssueModel oldItem, @NonNull IssueModel newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull IssueModel oldItem, @NonNull IssueModel newItem) {
                return oldItem.equals(newItem);
            }
        });
    }

    public void submitList(List<IssueModel> data) {
        differ.submitList(data);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_issue, parent, false);
        return new IssueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        IssueModel issueModel = differ.getCurrentList().get(position);
        if (issueModel != null) {
            ((IssueViewHolder) holder).bindData(context, issueModel);
        }
    }

    @Override
    public int getItemCount() {
        List<IssueModel> currentList = differ.getCurrentList();
        return currentList != null ? currentList.size() : 0;
    }
}
