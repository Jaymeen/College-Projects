package com.example.demo.webservices;

import com.example.demo.Entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Struct;
import java.util.List;
import java.util.Optional;


@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public Optional<?> fetchStudent(Long id){
        return repository.findById(id);
    }

    public void addStudent(Student student){
        repository.save(student);
    }

    public List<Student> getAllStudent() { return repository.findAll(); }

}