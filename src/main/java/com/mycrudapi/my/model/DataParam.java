package com.mycrudapi.my.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class DataParam {

    private int id;

    private String name;

    private String place;

    private String enteredAt;

    private String updatedAt;

    private String modifiedBy;

    private Date admissionDate;

}
