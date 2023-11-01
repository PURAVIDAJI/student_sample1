package com.example.sample.webcontroller;

import com.example.sample.model.Student;
import com.example.sample.model.StudentDto;
import com.example.sample.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/view")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/list")
    public String viewAllPosts(Model m){
        List<Student> students = studentService.viewAll();
        m.addAttribute("students",students);
        //스프링의 모델 객체 사용해서 모델안에 students리스트를 "students"라는 이름에 담아서 보냄.
        return "student/studentList";
    }

    @PostMapping("/list")
    public String insertStudent(@ModelAttribute Student student){
        studentService.insertStudent(student);
        return "redirect:/view/list";

    }

    @GetMapping("/detail/{studentId}")
    public String viewById(@PathVariable int studentId, Model m){
        //html 파일에서 studentId 정보로 받아옴.
        Student student = studentService.viewById(studentId);
        m.addAttribute("student",student);
        //스프링의 모델 객체에 담아서 타임리프로 보냄
        return "student/studentDetail";
    }

    @PostMapping("/delete/{studentId}")
    public String deleteById(@PathVariable int studentId){
        studentService.deleteById(studentId);
        return "redirect:/view/list";
    }

    @GetMapping("/update/{studentId}")
    public String updateStudent(@PathVariable int studentId, Model m){
        Student clicked = studentService.viewById(studentId);
        m.addAttribute("selected",clicked);
        //모델 객체에 담아서 정보 보내야함.
        return"student/studentEdit";
    }
    //업데이트만 하면됨.
    @PostMapping("/update/{studentId}")
    public String updateStudent(@PathVariable int studentId,
                                @ModelAttribute StudentDto studentDto){
        studentService.updateStudent(studentId,studentDto);

        return "redirect:/view/list";

    }

}
