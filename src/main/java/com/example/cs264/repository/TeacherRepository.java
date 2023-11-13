package com.example.cs264.repository;

import com.example.cs264.model.Student;
import com.example.cs264.model.Subject;
import com.example.cs264.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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

    public List<List<Student>> getStudentByTeacher(String teacher){
        try {
            List<List<Student>> students = new ArrayList<>();
            String sqlStudent = "SELECT * FROM Subject where subjectTeacher = ?";
            List<Subject> subjects = jdbcTemplate.query(sqlStudent, new BeanPropertyRowMapper<>(Subject.class), teacher);
            String sqlSubject = "SELECT * FROM Student where studentID=?";

            for (Subject s : subjects) {
                List<Student> student = jdbcTemplate.query(sqlSubject, new BeanPropertyRowMapper<>(Student.class), s.getStudentID());
                students.add(student);
            }
            for (List<Student> a: students){
                sqlSubject = "SELECT * FROM Subject where studentId = ? AND registeration_type = ?";

                for (Student s : a) {
                    List<Subject> addSubjectList = jdbcTemplate.query(sqlSubject, new BeanPropertyRowMapper<>(Subject.class), s.getStudentId(), "Register");
                    s.setAddSubjectList(addSubjectList.toArray(new Subject[0]));
                }
                for(Student s : a) {
                    List<Subject> dropSubjectList = jdbcTemplate.query(sqlSubject, new BeanPropertyRowMapper<>(Subject.class), s.getStudentId(), "Withdraw");
                    s.setDropSubjectList(dropSubjectList.toArray(new Subject[0]));
                }

            }

            return students;

        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
}