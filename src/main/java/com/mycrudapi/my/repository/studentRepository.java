package com.mycrudapi.my.repository;

import com.mycrudapi.my.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface studentRepository extends JpaRepository<Student, Integer> {

}
