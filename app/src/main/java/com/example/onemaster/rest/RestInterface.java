package com.example.onemaster.rest;

import com.example.onemaster.models.IssueModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestInterface {

    @GET("issues")
    Call<List<IssueModel>> getAllIssues(@Query("per_page") Integer per_page, @Query("page") Integer page);

}
