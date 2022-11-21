package com.mycrudapi.my.controller;

import com.mycrudapi.my.model.DataRequest;
import com.mycrudapi.my.model.DataResponse;
import com.mycrudapi.my.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
public class DataController {

    @Autowired
    DataService service;

    @PostMapping
    DataResponse response(@RequestBody DataRequest request){
        return service.response(request);
    }

    @PutMapping
    DataResponse update(@RequestBody DataRequest request){
        return service.update(request);
    }

    @GetMapping("by-date")
    DataResponse getByAdmissionDate(@RequestBody DataRequest request){
        return service.getByAdmissionDate(request);
    }

}
