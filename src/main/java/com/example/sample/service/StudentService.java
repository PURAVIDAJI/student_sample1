package com.example.sample.service;

import com.example.sample.model.Student;
import com.example.sample.model.StudentDto;
import com.example.sample.repository.StudentRepository;
import com.example.sample.repository.StudentRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> viewAll(){
        return new ArrayList<>(studentRepository.viewAll());
    }

    public Student viewById(int studentId){
        return studentRepository.viewById(studentId);
    }

    public String deleteById(int studentId){
        String res="";
        Student deleted = studentRepository.deleteById(studentId);

        if(deleted !=null){
            res = "삭제 완료";
        }else {
            res = "삭제 오류";
        }
        return res;
    }

    public Student insertStudent(Student student){
        String res="";
        Integer i = studentRepository.insertStudent(student);
        //인서트 된 항목의 id값이 담김..!
        Student updated = viewById(i);
        if(i !=null){
            res="추가 완료";
        }
        System.out.println(res);

        return updated;
        //student를 넣어도 되긴됨. 레포지토리에서
    }


    public String updateStudent(int studentId, StudentDto studentDto){
        //service를 이용해서 전공을 수정하는 로직 구현
        Student found = studentRepository.viewById(studentId);
        found.setMajor(studentDto.getMajor());
        studentRepository.updateStudent(studentId,found);

        return "수정완료";

    }

}
