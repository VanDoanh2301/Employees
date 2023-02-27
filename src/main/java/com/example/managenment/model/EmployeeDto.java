package com.example.managenment.model;

import com.example.managenment.domain.Department;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private int employeeId;
    private String name;
    private  String email;
    private  String password;
    private  int phone;
    private String address;

    private Department department;

    private boolean edit=false;
}
