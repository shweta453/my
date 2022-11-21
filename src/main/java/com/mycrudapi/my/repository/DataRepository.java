package com.mycrudapi.my.repository;

import com.mycrudapi.my.entity.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface DataRepository extends JpaRepository<Data, Integer > {

    Data findByadmissionDate(Date admissionDate);
}
