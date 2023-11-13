package com.example.cs264.repository;

import com.example.cs264.model.Student;
import com.example.cs264.model.Subject;
import com.example.cs264.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeacherRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String getTeacherByEmail(String email){
        try {
            String sqlTeacher = "SELECT * FROM Teacher where email = ?";
            List<Teacher> teacher = jdbcTemplate.query(sqlTeacher, new BeanPropertyRowMapper(Teacher.class), email);
            String teacherName = teacher.get(0).getTeacherName();
            return teacherName;

        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
}
