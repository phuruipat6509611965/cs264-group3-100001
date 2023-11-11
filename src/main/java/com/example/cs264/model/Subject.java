package com.example.cs264.model;
import lombok.*;

@Getter
@Setter
public class Subject {
    private String studentId;
    private String subjectCode;
    private String subjectName;
    private String subjectSection;
    private String subjectDate;
    private String subjectCredit;
    private String subjectTeacher;
    private boolean subjectTeacherCheck;
    private String type;
}
