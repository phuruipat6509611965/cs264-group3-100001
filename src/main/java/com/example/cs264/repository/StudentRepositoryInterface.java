package com.example.cs264.repository;

import com.example.cs264.model.Student;

import java.util.List;

public interface StudentRepositoryInterface {
    public List<Student> getStudentByEmail(String email);
    public void createStudent(Student student);
    public void updateStudent(Student student, String id);

    public List<List<Student>> getStudentByTeacher(String teacher);

}
