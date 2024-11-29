package com.example.demo.auxpackage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuxEmployeeDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String title;
    private String photograph;
    private Long deptId;

}