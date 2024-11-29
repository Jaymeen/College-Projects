package com.example.demo.controller;

import com.example.demo.Entity.AddTa;
import com.example.demo.Entity.AuthenticationObject;
import com.example.demo.webservices.AuthenticationService;
import com.example.demo.webservices.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/home")
public class MainController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private FacultyService facultyService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationObject credentials){
        credentials.setMode(1);
        if(authenticationService.authenticateFaculty(credentials.getUsername(), credentials.getPassword())){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addTa")
    public ResponseEntity<?> addTaByFaculty(@RequestBody AddTa addTa){
        Integer response = facultyService.addTaByFaculty(addTa);
        System.out.println("response is : " + response);
        if(response == 1)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
