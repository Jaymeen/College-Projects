package com.example.demo.controller;

import com.example.demo.Entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.webservices.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "api/v1/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<?>> getStudentFromId(@PathVariable("id") Long id){
        Optional<?> student = studentService.fetchStudent(id);
        if(student.isPresent()){
            return new ResponseEntity<>(student, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> addStudent(Student student){
        studentService.addStudent(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all-students")
    public ResponseEntity<List<Student>> getAllStudent() {
        try {
            List<Student> result = studentService.getAllStudent();
            return ResponseEntity.of(Optional.ofNullable(result));
        }
        catch(Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}