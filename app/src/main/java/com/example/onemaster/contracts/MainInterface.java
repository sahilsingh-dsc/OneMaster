package com.example.onemaster.contracts;

import com.example.onemaster.models.IssueModel;

import java.util.List;

public interface MainInterface {

    void onContractProgress();
    void onContractResponse(List<IssueModel> issueModel);
    void onContractNotFound();
    void onContractFailure();

}
