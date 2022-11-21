package com.mycrudapi.my.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class DataResponse {

    List<DataParam> dataParamList;
    String msg;
    int msgCode;

}
