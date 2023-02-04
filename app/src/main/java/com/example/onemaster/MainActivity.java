package com.example.onemaster;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.onemaster.adapters.IssueRecyclerAdapter;
import com.example.onemaster.adapters.IssueRecyclerNormalAdapter;
import com.example.onemaster.contracts.MainContract;
import com.example.onemaster.contracts.MainInterface;
import com.example.onemaster.databinding.ActivityMainBinding;
import com.example.onemaster.models.IssueModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Context context;
    private MainContract mainContract;
    private IssueRecyclerAdapter adapter;
    private int lastVisibleItemPosition;
    private Integer pageIndex = 0;
    List<IssueModel> issueModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = MainActivity.this;
        initView();
    }
    private void initView() {
        issueModelList = new ArrayList<>();
        binding.recyclerIssue.setLayoutManager(new LinearLayoutManager(context));
        initContract();
        initAdapter();
        mainContract.getAllIssue(pageIndex);
    }

    private void initAdapter() {
        adapter = new IssueRecyclerAdapter(context);
        binding.recyclerIssue.setAdapter(adapter);

        binding.recyclerIssue.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                lastVisibleItemPosition= ((LinearLayoutManager) binding.recyclerIssue.getLayoutManager()).findLastVisibleItemPosition();
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) binding.recyclerIssue.getLayoutManager();
                if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == adapter.getItemCount() - 1) {
                    pageIndex++;
                    mainContract.getAllIssue(pageIndex);
                }
            }
        });

    }

    private void initContract() {
        mainContract = new MainContract(context, new MainInterface() {
            @Override
            public void onContractProgress() {

            }

            @Override
            public void onContractResponse(List<IssueModel> issueModel) {
                issueModelList.addAll(issueModel);
                adapter.submitList(issueModelList);
                binding.recyclerIssue.scrollToPosition(lastVisibleItemPosition);
            }

            @Override
            public void onContractNotFound() {

            }

            @Override
            public void onContractFailure() {

            }
        });
    }
}