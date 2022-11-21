package com.mycrudapi.my.service;

import com.mycrudapi.my.model.studentRequest;
import com.mycrudapi.my.model.studentResponse;

import java.util.List;

public interface studentService {

    studentResponse response (studentRequest request);
    studentResponse getDataById(Integer id);
    public studentResponse update(studentRequest request);
    studentResponse getAllData();

    //trial
    studentResponse getData();

}
