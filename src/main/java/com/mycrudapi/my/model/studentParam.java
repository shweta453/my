package com.mycrudapi.my.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class studentParam {

    private int id;

    private String stuName;

    private Character stuGender;

}
