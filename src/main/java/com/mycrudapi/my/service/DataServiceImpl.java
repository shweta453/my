package com.mycrudapi.my.service;

import com.mycrudapi.my.activity.Impl.DataActivityImpl;
import com.mycrudapi.my.model.DataRequest;
import com.mycrudapi.my.model.DataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    DataActivityImpl activity;

    public DataResponse response(DataRequest request) {
        return activity.saveData(request);
    }

    @Override
    public DataResponse update(DataRequest request) {
        return activity.update(request);
    }

    @Override
    public DataResponse getByAdmissionDate(DataRequest request) {
        return activity.getByAdmissionDate(request);
    }

}
