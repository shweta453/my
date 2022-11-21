package com.mycrudapi.my.activity;

import com.mycrudapi.my.model.studentRequest;
import com.mycrudapi.my.model.studentResponse;

import java.util.List;

public interface studentActivity {

    studentResponse response (studentRequest request);

    studentResponse getDataById(Integer id);

    studentResponse update (studentRequest request);

    studentResponse getAllData();


    //trial
    studentResponse getData();

}
