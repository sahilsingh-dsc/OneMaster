package com.example.onemaster.contracts;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.onemaster.utils.Constant;
import com.example.onemaster.rest.RestClient;
import com.example.onemaster.rest.RestInterface;
import com.example.onemaster.models.IssueModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainContract {

    private Context context;
    private MainInterface mainInterface;
    private RestInterface restInterface;
    private static final String TAG = "MainContract";

    public MainContract(Context context, MainInterface mainInterface) {
        this.context = context;
        this.mainInterface = mainInterface;
        restInterface = RestClient.getRetrofitFabloUserService(context).create(RestInterface.class);
    }

    public void getAllIssue(Integer page) {
        mainInterface.onContractProgress();
        restInterface.getAllIssues(10, page)
                .enqueue(new Callback<List<IssueModel>>() {
                    @Override
                    public void onResponse(Call<List<IssueModel>> call, Response<List<IssueModel>> response) {
                        if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                            if (response.body() != null) {
                                mainInterface.onContractResponse(response.body());
                            }
                        } else if (response.code() == Constant.HTTP_CLIENT_ERROR) {
                            mainInterface.onContractNotFound();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<IssueModel>> call, Throwable t) {
                        mainInterface.onContractFailure();
                        Log.e(TAG, "onFailure: "+t.getMessage());
                    }
                });


    }

}
