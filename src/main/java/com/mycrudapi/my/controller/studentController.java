package com.mycrudapi.my.controller;

import com.mycrudapi.my.model.studentRequest;
import com.mycrudapi.my.model.studentResponse;
import com.mycrudapi.my.service.studentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1")
public class studentController {

    @Autowired
    private studentService service;

    @PostMapping("/save-data")
    public studentResponse response(@RequestBody studentRequest request) {
        log.info("Inside controller");
        return service.response(request);
    }

    @GetMapping("/get-data")
    public studentResponse getDataById(@RequestParam("id") int id) {
        log.info("inside controller");
        return service.getDataById(id);
    }


    @PutMapping
    public studentResponse update(@RequestBody studentRequest request){
        return service.update(request);
    }

    @GetMapping
    public studentResponse getAllData(){
        return service.getAllData();
    }

    @GetMapping("/get")
    studentResponse getData(){
        return service.getData();
    }

}
