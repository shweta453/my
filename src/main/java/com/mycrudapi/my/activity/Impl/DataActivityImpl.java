package com.mycrudapi.my.activity.Impl;

import com.mycrudapi.my.activity.DataActivity;
import com.mycrudapi.my.entity.Data;
import com.mycrudapi.my.enumeration.ResponseCode;
import com.mycrudapi.my.enumeration.ResponseMessageType;
import com.mycrudapi.my.model.DataParam;
import com.mycrudapi.my.model.DataRequest;
import com.mycrudapi.my.model.DataResponse;
import com.mycrudapi.my.repository.DataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Slf4j
public class DataActivityImpl implements DataActivity {

    @Autowired
    DataRepository dataRepository;

    private String modifiedBy="SYSTEM";

    @Override
    public DataResponse saveData(DataRequest request) {
        DataResponse dataResponse = DataResponse.builder().build();
        try {
            if (request.getParam().getName() == null || request.getParam().getName().isEmpty()) {
                dataResponse = DataResponse.builder()
                        .msg(ResponseMessageType.VALIDATION.name())
                        .msgCode(ResponseCode.EMPTY.getCode())
                        .build();
            }
            if (request.getParam().getPlace().isEmpty() || request.getParam().getPlace() == null) {
                dataResponse = DataResponse.builder()
                        .msg(ResponseMessageType.VALIDATION.name())
                        .msgCode(ResponseCode.EMPTY.getCode())
                        .build();
            } else {
                Data data = Data.builder()
                        .name(request.getParam().getName())
                        .place(request.getParam().getPlace())
                        .enteredAt(format())
                        .admissionDate(request.getParam().getAdmissionDate())
                        .build();
                data = dataRepository.save(data);
                List<DataParam> paramList=new ArrayList<>();
                DataParam param=DataParam.builder().build();
                BeanUtils.copyProperties(data,param);
                paramList.add(param);
                dataResponse = DataResponse.builder()
                        .dataParamList(paramList)
                        .msg(ResponseMessageType.SUCCESS.name())
                        .msgCode(ResponseCode.SUCCESS.getCode())
                        .build();
            }
        } catch (Exception e) {
            log.debug(e.getMessage());
            dataResponse = DataResponse.builder()
                    .msg(ResponseMessageType.ERROR.name())
                    .msgCode(ResponseCode.FAIL.getCode())
                    .build();
        }
        return dataResponse;
    }

    public DataResponse update(DataRequest request) {
        Optional<Data> optional = dataRepository.findById(request.getParam().getId());
        DataResponse response = DataResponse.builder().build();
        List<DataParam> dataParamList = new ArrayList<>();
        DataParam param=DataParam.builder().build();
        try {
            if (optional.isEmpty()) {
                response = DataResponse.builder()
                        .msg("No such id exists")
                        .msgCode(ResponseCode.DAO_DATABASE.getCode())
                        .build();
            } else {
                Data data=Data.builder()
                        .id(optional.get().getId())
                        .name(request.getParam().getName())
                        .place(request.getParam().getPlace())
                        .enteredAt(optional.get().getEnteredAt())
                        .updatedAt(format())
                        .modifiedBy(modifiedBy)
                        .admissionDate(request.getParam().getAdmissionDate())
                        .build();
                data=dataRepository.save(data);
                BeanUtils.copyProperties(data,param);
                dataParamList.add(param);
                response = DataResponse.builder()
                        .dataParamList(dataParamList)
                        .msg("Data Updated Successfully")
                        .msgCode(ResponseCode.SUCCESS.getCode())
                        .build();
            }
        } catch (Exception e) {
            response = DataResponse.builder()
                    .msg(e.getMessage())
                    .msgCode(ResponseCode.DAO_DATABASE.getCode())
                    .build();
        }
        return response;
    }

    @Override
    public DataResponse getByAdmissionDate(DataRequest request) {
        List<DataParam> paramList = new ArrayList<>();
        DataParam param = DataParam.builder().build();
        DataResponse response = DataResponse.builder().build();
        Data data = dataRepository.findByadmissionDate(request.getParam().getAdmissionDate());
        try {
            if (data != null) {
                BeanUtils.copyProperties(data, param);
                paramList.add(param);
                response = DataResponse.builder()
                        .dataParamList(paramList)
                        .msg("Data fetched successfully")
                        .msgCode(ResponseCode.SUCCESS.getCode())
                        .build();
            } else {
                response = DataResponse.builder()
                        .msg("No such data exists")
                        .msgCode(ResponseCode.EMPTY.getCode())
                        .build();
            }
        } catch (Exception e) {
            response = DataResponse.builder()
                    .msg("Data could not be fetched")
                    .msgCode(ResponseCode.SUCCESS.getCode())
                    .build();
        }
        return response;
    }


    String format(){
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        return timeStamp;
    }



}
