package com.example.cs264.controller;

import com.example.cs264.model.Login;
import com.example.cs264.model.Student;
import com.example.cs264.repository.LoginRepository;
import com.example.cs264.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@CrossOrigin
public class registerPageController {

    @Autowired
    LoginRepository loginRepository;
    @Autowired
    StudentRepository studentRepository;

    @PostMapping("/login")
    public boolean loginConfirm(@RequestBody Login login){
        return loginRepository.loginConfirmation(login.getEmail(), login.getPassword());
    }

    @PostMapping("/add")
    public void addStudent(@RequestBody Student student) {
        studentRepository.createStudent(student);
    }

    @GetMapping("/req")
    public List<Student> reqStudent(@RequestParam(name = "email", required = true) String email){
        return studentRepository.getStudentByEmail(email);
    }

    @PostMapping("/update")
    public void updateStudent(@RequestBody Student  student, @RequestParam String studentId){
        studentRepository.updateStudent(student, studentId);
    }

    @GetMapping("/tget")
    public List<List<Student>> getStudentByTeacher(@RequestParam(name = "teacher", required = true) String teacher){
        return studentRepository.getStudentByTeacher(teacher);
    }

}
