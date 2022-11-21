package com.mycrudapi.my.activity;

import com.mycrudapi.my.model.DataRequest;
import com.mycrudapi.my.model.DataResponse;

import java.text.ParseException;

public interface DataActivity {

    DataResponse saveData(DataRequest request);
    DataResponse update(DataRequest request);


    DataResponse getByAdmissionDate(DataRequest request);
}
