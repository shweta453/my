package com.mycrudapi.my.service.Impl;

import com.mycrudapi.my.activity.studentActivity;
import com.mycrudapi.my.model.studentRequest;
import com.mycrudapi.my.model.studentResponse;
import com.mycrudapi.my.service.studentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class studentServiceImpl implements studentService {

    @Autowired
    private studentActivity activity;

    @Override
    public studentResponse response(studentRequest request) {
        log.info("inside service");
        return activity.response(request);
    }

    @Override
    public studentResponse getDataById(Integer id) {
        log.info("inside service");
        return activity.getDataById(id);
    }

    @Override
    public studentResponse update(studentRequest request) {
        return activity.update(request);
    }

    @Override
    public studentResponse getAllData() {
        return activity.getAllData();
    }

    @Override
    public studentResponse getData() {
        return activity.getData();
    }
}
