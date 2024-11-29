package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Courses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Course_Id")
    private Long courseId;

    @Column(name = "Course_Code")
    private String courseCode;

    @Column(name = "Course_Name")
    private String courseName;

    private Specialization specialization;

    @Column(name = "Credits")
    private Integer credit;

    @ManyToOne
    @JoinColumn(name = "Faculty_id", referencedColumnName = "faculty_id")
    private Faculty faculty;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "Course_TA", joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "Course_Id"), inverseJoinColumns = @JoinColumn(name = "ta_id", referencedColumnName = "student_id"))
    private Set<Student> ta = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "course_student", joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "Course_Id"), inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "student_id"))
    private Set<Student> students = new HashSet<>();

    @Column(name = "max_students")
    private Integer maxStudents;

    @Column(name = "max_ta")
    private Integer maxTA;


}