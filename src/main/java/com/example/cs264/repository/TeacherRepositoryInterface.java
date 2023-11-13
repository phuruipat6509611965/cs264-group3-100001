package com.example.cs264.repository;

import com.example.cs264.model.Student;
import com.example.cs264.model.Teacher;

import java.util.List;

public interface TeacherRepositoryInterface {
    public String getTeacherByEmail(String email);
    public List<List<Student>> getStudentByTeacher(String teacher);
}
