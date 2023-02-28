package com.example.managenment.model;

import com.example.managenment.domain.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalaryDto {
    private int salaryId;

    private int count;

    private double bonus;

    private double minus;
    private double total;
    private int month;
    private int year;


    private Employee employee;

    public double getTotal() {
        total = 1000*count + bonus - minus;
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
