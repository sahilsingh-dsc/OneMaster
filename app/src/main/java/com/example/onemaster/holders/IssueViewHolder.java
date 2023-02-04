package com.example.onemaster.holders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onemaster.R;
import com.example.onemaster.models.IssueModel;

public class IssueViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private IssueModel issueModel;

    TextView tvId;
    TextView tvTitle;
    TextView tvUserName;
    public IssueViewHolder(@NonNull View itemView) {
        super(itemView);
        tvId = itemView.findViewById(R.id.tvIssueId);
        tvTitle = itemView.findViewById(R.id.tvIssueTitle);
        tvUserName = itemView.findViewById(R.id.tvUserLogin);
    }

    public void bindData(Context mContext, IssueModel issueModelData) {
        context = mContext;
        issueModel = issueModelData;
        initView();
    }

    private void initView() {
        tvId.setText(issueModel.getId()+"");
        tvTitle.setText(issueModel.getTitle());
        tvUserName.setText(issueModel.getUser().getLogin());
    }
}
