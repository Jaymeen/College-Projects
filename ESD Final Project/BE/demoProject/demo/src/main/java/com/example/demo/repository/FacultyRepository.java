package com.example.demo.repository;

import com.example.demo.Entity.Courses;
import com.example.demo.Entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Optional<Faculty> findByEmail(String email);

//    Optional<Courses> findCoursesByFacCode(String facCode);
    Optional<Faculty> findByFacCode(String facCode);
    Optional<Faculty> findByName(String name);
}