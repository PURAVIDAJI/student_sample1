package com.example.sample.repository;

import com.example.sample.model.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper

public interface StudentRepository {

    List<Student> viewAll();
    Student viewById(int studentId);

    Student deleteById(int studentId);

    Integer insertStudent(Student student);

    Student updateStudent(int studentId,Student student);

}
