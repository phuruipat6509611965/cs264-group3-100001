package com.example.cs264.repository;

import com.example.cs264.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoginRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public boolean loginConfirmation(String emailIn, String passwordIn) {
        String sql = "SELECT * FROM Login WHERE email = ? AND password = ?";
        List<Student> students = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Student.class), new Object[]{emailIn, passwordIn});
        return !students.isEmpty();
    }
}
