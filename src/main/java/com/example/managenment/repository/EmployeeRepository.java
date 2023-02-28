package com.example.managenment.repository;

import com.example.managenment.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    public boolean existsByName(String name);
    public  boolean existsByEmail(String email);
    @Query(value = "select *from employee where department_id=?1", nativeQuery = true)
    public List<Employee> getByDepartmentId(Integer id);
}
