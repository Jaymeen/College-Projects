package com.example.demo.auxpackage;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuxCourseDTO{
    private String courseCode;
    private String courseName;
    private Integer specialization;
    private Integer credit;
    private String facId;
    private Integer maxStudents;
    private Integer maxTa;
}