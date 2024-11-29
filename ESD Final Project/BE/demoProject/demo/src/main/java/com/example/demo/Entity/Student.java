package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "Student")
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "student_rollNo")
    private String studentRollNo;

    @Column(name = "name")
    private String studentName;

    @Column(name = "gender")
    private char gender;

    @Column(name = "login")
    private String loginId;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @JsonIgnore
    @ManyToMany(mappedBy = "students")
    private Set<Courses> coursesTaken = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "ta")
    private Set<Courses> courseTa = new HashSet<>();
}