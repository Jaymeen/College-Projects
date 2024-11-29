package com.example.demo.auxpackage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuxDeptDTO {

    private String deptName;
    private Integer deptCapacity;
}