package com.example.cs264.controller;

import com.example.cs264.model.Student;
import com.example.cs264.repository.LoginRepository;
import com.example.cs264.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/students")
public class registerPageController {

    @Autowired
    LoginRepository loginRepository;
    @Autowired
    StudentRepository studentRepository;

    @PostMapping("/login")
    public boolean loginConfirm(@RequestParam(name = "email", required = true) String email,@RequestParam(name = "password", required = true) String password){
        return loginRepository.loginConfirmation(email, password);
    }

    @PostMapping("/add")
    public void addStudent(@RequestBody Student student) {
        studentRepository.createStudent(student);
    }

    @GetMapping("/req")
    public List<Student> reqStudent(@RequestParam(name = "email", required = true) String email){
        return studentRepository.getStudentByEmail(email);
    }

}
