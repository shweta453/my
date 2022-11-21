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
public class studentResponse {

    private studentParam param;
    private List<studentParam> paramList;
    private String msg;
    private int msgCode;

}
