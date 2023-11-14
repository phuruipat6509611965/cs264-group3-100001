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
@RequestMapping("/api/student")
@CrossOrigin
public class registerPageController {

    @Autowired
    LoginRepository loginRepository;
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherRepository teacherRepository;


    //Login authentication by input username(studentID) and password through body
    @PostMapping("/login")
    public String loginConfirm(@RequestBody Login login){
        return loginRepository.loginConfirmation(login);
    }

    //Create new student subject data
    @PostMapping("/add")
    public void addStudent(@RequestBody Student student) {
        studentRepository.createStudent(student);
    }

    //Get student by username(studentID)
    @GetMapping("/req")
    public List<Student> reqStudent(@RequestParam(name = "username", required = true) String username){
        return studentRepository.getStudentByEmail(username);
    }

    @PostMapping("/update")
    public void updateStudent(@RequestBody Student  student, @RequestParam String studentId){
        studentRepository.updateStudent(student, studentId);
    }

    @GetMapping("/del")
    public void deleteById(@RequestParam String studentId){
        studentRepository.deleteById(studentId);
    }

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
