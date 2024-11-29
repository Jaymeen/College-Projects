package com.example.demo.auxpackage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuxStudentDTO {

    private String name;
    private char gender;
    private String loginId;
    private String password;
    private String rollNo;
}