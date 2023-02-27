package com.example.managenment.repository;

import com.example.managenment.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    public boolean existsByName(String name);
    public  boolean existsByEmail(String email);
}
