package com.example.managenment.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int departmentId;
    @Column(name = "departmentName")
    private  String name;

    @Column(name = "shortName")
    private  String shortName;

    @OneToMany(mappedBy = "department")
    private Collection<Employee> employees;

    public Department(int departmentId, String name, String shortName) {
        this.departmentId = departmentId;
        this.name = name;
        this.shortName = shortName;
    }
}
