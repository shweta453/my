package com.mycrudapi.my.activity.Impl;

import com.mycrudapi.my.activity.studentActivity;
import com.mycrudapi.my.entity.Student;
import com.mycrudapi.my.model.studentParam;
import com.mycrudapi.my.model.studentRequest;
import com.mycrudapi.my.model.studentResponse;
import com.mycrudapi.my.repository.studentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class studentActivityImpl implements studentActivity {

    @Autowired
    private studentRepository repository;

    @Override
    public studentResponse response(studentRequest request) {
        log.info("inside activity");
        studentResponse response = studentResponse.builder().build();
        studentParam param = studentParam.builder().build();
        try {
            Optional<Student> studentOptional = repository.findById(request.getParam().getId());
            if (!studentOptional.isPresent()) {
                Student student = Student.builder().id(request.getParam().getId()).stuName(request.getParam().getStuName()).stuGender(request.getParam().getStuGender()).build();
                student = repository.save(student);
                response = studentResponse.builder().param(param.toBuilder().id(request.getParam().getId()).stuName(request.getParam().getStuName()).stuGender(request.getParam().getStuGender()).build()).msg("Data saved Successfully").msgCode(6).build();
            } else {
                response = studentResponse.builder().msg("Please Enter a value").build();
            }
        } catch (Exception e) {
            log.debug(e.getMessage());
            response = studentResponse.builder().msg("Data couldn't be added").build();
        }
        return response;
    }

    @Override
    public studentResponse getDataById(Integer id) {
        studentResponse response = studentResponse.builder().build();
        Optional<Student> student = repository.findById(id);
        try {
            if (student.isEmpty()) {
                response = studentResponse.builder().msg("No such id present").build();
            } else {
                response = studentResponse.builder().param(studentParam.builder().id(student.get().getId()).stuName(student.get().getStuName()).stuGender(student.get().getStuGender()).build()).msg("Data fetched succesfully").msgCode(6).build();
            }
        } catch (Exception e) {
            log.debug(e.getMessage());
            response = studentResponse.builder().msg("Error data couldn't be fetched").build();
        }
        return response;
    }

    @Override
    public studentResponse update(studentRequest request) {
        studentResponse response = studentResponse.builder().build();
        Student student = Student.builder().build();
        studentParam param = studentParam.builder().build();
        try {
            Optional<Student> studentOptional = repository.findById(request.getParam().getId());
            if (studentOptional.isEmpty()) {
                response = studentResponse.builder()
                        .msg("No such id present")
                        .msgCode(0)
                        .build();
            } else {
                student = Student.builder()
                        .id(request.getParam().getId())
                        .stuName(request.getParam().getStuName())
                        .stuGender(request.getParam().getStuGender())
                        .build();
                student = repository.save(student);
                response = studentResponse.builder()
                        .param(param.toBuilder()
                                .id(request.getParam().getId())
                                .stuName(request.getParam().getStuName())
                                .stuGender(request.getParam().getStuGender())
                                .build())
                        .msg("Data Updated successfully...")
                        .msgCode(6)
                        .build();
            }
        } catch (Exception e) {
            log.debug(e.getLocalizedMessage());
            response = studentResponse.builder()
                    .msg("Data couldn't be updated")
                    .msgCode(0)
                    .build();
        }
        return response;
    }

    @Override
    public studentResponse getAllData() {
        /*List<Student> students = repository.findAll();
        return students.stream().filter(Objects::nonNull)
                .map(mapper -> studentResponse.builder()
                        .param(studentParam.builder()
                                .id(mapper.getId())
                                .stuName(mapper.getStuName())
                                .stuGender(mapper.getStuGender())
                                .build())
                        .msg("Data fetched Successfully")
                        .msgCode(6)
                        .build()).collect(Collectors.toList());*/
        List<Student> students = repository.findAll();
        studentResponse response=studentResponse.builder().build();
        List<studentParam>studentParamList=new ArrayList<>();
        //studentParam sp= studentParam.builder().build();
        students.forEach(student -> {
            studentParam sp = studentParam.builder()
                    .id(student.getId())
                    .stuName(student.getStuName())
                    .stuGender(student.getStuGender())
                    .build();
            studentParamList.add(sp);
        });
        response=studentResponse.builder()
                .paramList(studentParamList)
                .msg("fetched successfully")
                .msgCode(6)
                .build();
        return response;
    }

    @Override
    public studentResponse getData() {
        List<Student> students = repository.findAll();
        studentResponse response = studentResponse.builder().build();
        List<studentParam> paramList = new ArrayList();
        try {
            if (!students.isEmpty()) {
               students.stream().filter(Objects::nonNull).forEach(e -> {
                    studentParam param = studentParam.builder().build();
                    BeanUtils.copyProperties(e, param);
                    param.setId(e.getId());
                    param.setStuName(e.getStuName());
                    param.setStuGender(e.getStuGender());
                    paramList.add(param);
                });
                response = studentResponse.builder()
                        .paramList(paramList)
                        .msg("Data fetched Successfully")
                        .msgCode(6)
                        .build();
            } else {
                response = studentResponse.builder()
                        .msg("No data exists")
                        .msgCode(0)
                        .build();
            }
        } catch (Exception e) {
            response = studentResponse.builder()
                    .msg("Data could not be fetched")
                    .msgCode(0)
                    .build();
        }

        return response;
    }


}

