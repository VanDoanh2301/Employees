package com.example.managenment.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
public class Employee {
    @Id
    @Column(name="employeeId")
    private int employeeId;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private  String email;

    @Column(name="password")
    private  String password;

    @Column(name = "phone")
    private  int phone;

    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name="department_Id")
    private Department department;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "employees_roles",
            joinColumns = @JoinColumn(name = "user_Id",nullable = false),
            inverseJoinColumns = @JoinColumn(name = "role_Id")
    )
    private List<Role> roles;

    @OneToMany(mappedBy = "employee")
    private Collection<Salary> salaries;

    @Override
    public String toString() {
        return name;
    }
}