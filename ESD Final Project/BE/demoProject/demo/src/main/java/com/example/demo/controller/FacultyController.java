package com.example.demo.controller;

import com.example.demo.Entity.Courses;
import com.example.demo.Entity.Faculty;
import com.example.demo.Entity.Student;
import com.example.demo.repository.CoursesRepository;
import com.example.demo.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@CrossOrigin
@Controller
@RequestMapping(path = "api/v1/faculty")
public class FacultyController {
    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private CoursesRepository coursesRepository;

    @PostMapping("/courses")
    public ResponseEntity<Set<Courses>> getAllCoursesByAFacultyCode(@RequestBody Faculty facCode){
        System.out.println("the faculty code is: " + facCode.getFacCode());
        try {
            String code = facCode.getFacCode();
            Optional<Faculty> faculty = facultyRepository.findByFacCode(code);
            System.out.println("the course name is: " + faculty.get().getName());
            return ResponseEntity.of(Optional.ofNullable(faculty.get().getCourses()));
        }catch(Exception e) {
            System.out.println("Error in tne courdes maping is : " + e);
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/ta-list")
    public ResponseEntity<Set<Student>> getAllTAByCourseId(@RequestBody Courses courseId) {
        System.out.println("the course_id id: " + courseId.getCourseId());
        System.out.println("get ta by course_id: " + courseId.getCourseId());
        System.out.println("the The TA list size is: " + courseId.getTa().size());
        try {
            Optional<Courses> course = coursesRepository.findCoursesByCourseId(courseId.getCourseId());
            Set<Student> result = course.get().getTa();
            return ResponseEntity.of(Optional.ofNullable(result));
        }catch(Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
