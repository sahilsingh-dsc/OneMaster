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

public class IssueRecyclerNormalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<IssueModel> issueModelList;

    public IssueRecyclerNormalAdapter(Context context, List<IssueModel> issueModelList) {
        this.context = context;
        this.issueModelList = issueModelList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_issue, parent, false);
        return new IssueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        IssueModel issueModel = issueModelList.get(position);
        if (issueModel != null) {
            ((IssueViewHolder) holder).bindData(context, issueModel);
        }
    }

    @Override
    public int getItemCount() {
        return issueModelList.size();
    }
}
