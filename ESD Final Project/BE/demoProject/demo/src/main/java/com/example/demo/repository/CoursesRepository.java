package com.example.demo.repository;

import com.example.demo.Entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CoursesRepository extends JpaRepository<Courses, Long> {
    Optional<Courses> findCoursesByFacultyFacCode(String facCode);
    Optional<Courses> findCoursesByCourseId(Long id);
}
