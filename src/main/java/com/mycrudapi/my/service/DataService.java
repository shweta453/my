package com.mycrudapi.my.service;

import com.mycrudapi.my.model.DataRequest;
import com.mycrudapi.my.model.DataResponse;

public interface DataService {
    DataResponse response(DataRequest request);
    DataResponse update(DataRequest request);
    DataResponse getByAdmissionDate(DataRequest request);

}
