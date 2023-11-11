package com.example.cs264.repository;

import com.example.cs264.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository implements StudentRepositoryInterface {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Student> getStudentByEmail(String email){
        String sqlStudent = "SELECT * FROM Student where studentId = ?";
        List<Student> students = jdbcTemplate.query(sqlStudent, new BeanPropertyRowMapper<>(Student.class), email);
        return students;
    }

}
