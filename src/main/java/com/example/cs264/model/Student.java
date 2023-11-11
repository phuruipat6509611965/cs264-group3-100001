package com.example.cs264.model;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class Student {
    private String date;
    private String studentFirstName;
    private String studentLastName;
    private String studentId;
    private String email;
    private int studentYear;
    private String studyField;
    private String advisor;
    private String addressNumber;
    private String moo;
    private String tumbol;
    private String amphur;
    private String province;
    private String postalCode;
    private String mobilePhone;
    private String phone;
    private String cause;
    private Subject[] addSubjectList;
    private Subject[] dropSubjectList;
}
