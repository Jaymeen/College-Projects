package com.example.demo.webservices;

import com.example.demo.Entity.AuthenticationObject;
import com.example.demo.Entity.Faculty;
import com.example.demo.Entity.Student;
import com.example.demo.repository.FacultyRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AuthenticationService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    public boolean authenticateFaculty(String username, String password){
        System.out.println("username: " + username);
        Optional<Faculty> facultyByEmail = facultyRepository.findByFacCode(username);
        System.out.println("facultyPresent: "+ facultyByEmail.isPresent());
        if(facultyByEmail.isPresent()){
            Faculty faculty = facultyByEmail.get();
            return password.equals(faculty.getPassword());
        }else{
            return false;
        }
    }

}