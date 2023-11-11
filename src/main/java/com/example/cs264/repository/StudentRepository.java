package com.example.cs264.repository;

import com.example.cs264.model.Student;
import com.example.cs264.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository implements StudentRepositoryInterface {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createStudent(Student student) {

        //Save student data into table
        String student_data = "Insert INTO Student (date, studentFirstName, studentLastName, studentId, email, studentYear,  studyField, advisor, addressNumber, moo, tumbol, amphur, province, postalCode, mobilePhone, phone ,cause)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(student_data, student.getDate(), student.getStudentFirstName(), student.getStudentLastName(), student.getStudentId(), student.getEmail(), student.getStudentYear(), student.getStudyField(), student.getAdvisor(), student.getAddressNumber(), student.getMoo(), student.getTumbol(), student.getAmphur(), student.getProvince(), student.getPostalCode(), student.getMobilePhone(), student.getPhone(), student.getCause());

        //Save subject
        String subject_data = "Insert INTO Subject (studentId, subjectCode, subjectName, subjectSection, subjectDate, subjectCredit, subjectTeacher, subjectTeacherCheck, registeration_type)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        //Register subject
        for(Subject s: student.getAddSubjectList()){
            jdbcTemplate.update(subject_data, student.getStudentId(), s.getSubjectCode(), s.getSubjectName(), s.getSubjectSection(), s.getSubjectDate(), s.getSubjectCredit(), s.getSubjectTeacher(), s.getSubjectTeacherCheck(), "Register");
        }
        //Withdraw subject
        for(Subject s: student.getDropSubjectList()){
            jdbcTemplate.update(subject_data, student.getStudentId(), s.getSubjectCode(), s.getSubjectName(), s.getSubjectSection(), s.getSubjectDate(), s.getSubjectCredit(), s.getSubjectTeacher(), s.getSubjectTeacherCheck(), "Withdraw");
        }


    }

    public List<Student> getStudentByEmail(String email) {
        try {
            String sqlStudent = "SELECT * FROM Student where email = ?";
            List<Student> students = jdbcTemplate.query(sqlStudent, new BeanPropertyRowMapper<>(Student.class), email);
            String sqlSubject = "SELECT * FROM Subject where studentId = ? AND registeration_type = ?";

            for (Student s : students) {
                List<Subject> addSubjectList = jdbcTemplate.query(sqlSubject, new BeanPropertyRowMapper<>(Subject.class), s.getStudentId(), "Register");
                s.setAddSubjectList(addSubjectList.toArray(new Subject[0]));
            }
            for (Student s : students) {
                List<Subject> dropSubjectList = jdbcTemplate.query(sqlSubject, new BeanPropertyRowMapper<>(Subject.class), s.getStudentId(), "Withdraw");
                s.setDropSubjectList(dropSubjectList.toArray(new Subject[0]));
            }

            return students;

        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
}
