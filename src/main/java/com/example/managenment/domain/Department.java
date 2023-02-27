package com.example.managenment.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "departments")
public class Department {
    @Id
    private  int departmentId;
    @Column(name = "department_Name",unique = true,columnDefinition = "nvarchar(100) not null")
    private  String name;

    @Column(name = "short_Name",unique = true,columnDefinition = "nvarchar(100) not null")
    private  String shortName;
    @OneToMany(mappedBy = "department")
    private Collection<Employee> employees;

}
