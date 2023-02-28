package com.example.managenment.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Getter
@Setter
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

    public Department(int departmentId, String name, String shortName) {
        this.departmentId = departmentId;
        this.name = name;
        this.shortName = shortName;
    }
}
