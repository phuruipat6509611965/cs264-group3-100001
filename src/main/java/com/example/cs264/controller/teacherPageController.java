package com.example.cs264.controller;

import com.example.cs264.model.Login;
import com.example.cs264.model.Student;
import com.example.cs264.repository.LoginRepository;
import com.example.cs264.repository.StudentRepository;
import com.example.cs264.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
@CrossOrigin

public class teacherPageController {
    @Autowired
    LoginRepository loginRepository;
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @GetMapping("/tget")
    public List<List<Student>> getStudentByTeacher(@RequestParam(name = "teacher", required = true) String teacher){
        return teacherRepository.getStudentByTeacher(teacher);
    }

    //Use to
    @GetMapping("/tcheck")
    public void teacherApprove(@RequestBody Student  student, @RequestParam boolean check, @RequestParam String subjectCode){
        teacherRepository.teacherApprove(student, check, subjectCode);
    }
}
