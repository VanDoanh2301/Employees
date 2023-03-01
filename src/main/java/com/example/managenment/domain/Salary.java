package com.example.managenment.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int salaryId;

    @Column(name="count", length = 120, nullable = false)
    private int count;
    @Column(name="bonus", length = 120, nullable = false)

    private double bonus;
    @Column(name="minus", length = 120, nullable = false)

    private double minus;
    @Column(name="total", length = 120, nullable = false)
    private double total;

    @Column(name="month", length = 120, nullable = false)
    private int month;
    @Column(name="year", length = 120, nullable = false)
    private int year;

    @ManyToOne
    @JoinColumn(name="employeeId")
    private Employee employee;

}
