package com.example.cs264.repository;

import com.example.cs264.model.Student;

import java.util.List;

public interface StudentRepositoryInterface {
    public List<Student> getStudentByEmail(String email);

}
