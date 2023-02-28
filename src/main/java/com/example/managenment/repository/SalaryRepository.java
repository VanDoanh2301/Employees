package com.example.managenment.repository;

import com.example.managenment.domain.Employee;
import com.example.managenment.domain.Salary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends JpaRepository<Salary,Integer> {
    Page<Salary> findAllByMonth(int month, Pageable pageable);
}
