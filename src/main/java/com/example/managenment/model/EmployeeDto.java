package com.example.managenment.model;

import com.example.managenment.domain.Department;
import com.example.managenment.domain.Role;
import lombok.*;

import java.util.List;

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

    private List<Role> roles;

    private boolean edit=false;
}
