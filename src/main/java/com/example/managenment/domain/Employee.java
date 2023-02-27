package com.example.managenment.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name="employeeId")
    @GeneratedValue(strategy = GenerationType.AUTO)
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

}