package com.example.sample.repository;

import com.example.sample.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Repository
public class StudentRepositoryImpl implements StudentRepository{

    private static Map<Integer,Student> studentMap = new HashMap<>();

    private static int seq =0;

    public StudentRepositoryImpl() {
        seq++;
        Student student = new Student();
        student.setStudentId(seq);
        student.setName("박치수");
        student.setMajor("사회기반시스템부");
        student.setSemester(6);
        student.setPoint(3.3F);

        studentMap.put(seq,student);
        //데이터가 한개 들어갔다.
    }

    //리포지터리가 DAO의 역할을 한다... 따라서 데이터베이스에서 직접 가져오는 역할을 한다고 보면된다.

    @Override
    public List<Student> viewAll() {

        return new ArrayList<>(studentMap.values());
    }

    @Override
    public Student viewById(int studentId) {

        Student matched = studentMap.get(studentId);
        return matched;
    }

    @Override
    public Student deleteById(int studentId) {

        Student deleted = studentMap.remove(studentId);
        //삭제된 애가 담기는 것이다.
        return deleted;
    }

    @Override
    public Integer insertStudent(Student student) {

        Integer result = null;
        student.setStudentId(++seq);
        Student inserted = studentMap.put(seq, student);

        return student.getStudentId();
        //inserted.getStudentId(); 하면 null이 될 수 있다->안추가될 수 있으니
    }

    @Override
    public Student updateStudent(int studentId,Student student) {
        if(viewById(studentId) !=null)
        studentMap.put(student.getStudentId(), student);

        return student;
    }
}
