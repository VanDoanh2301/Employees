package com.example.managenment.repository;

import com.example.managenment.domain.Department;
import com.example.managenment.domain.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    Page<Employee> findByNameContaining(String name, Pageable pageable);
    public boolean existsByName(String name);
    public  boolean existsByEmail(String email);
    @Query(value = "select *from employee where department_id=?1", nativeQuery = true)
    public Page<Employee> getByDepartmentId(Integer id , Pageable pageable);

    public Employee findByEmail(String email);

    @Query("SELECT e FROM Employee e where e.name=?1")
    Employee getByName(String username);
    @Query("SELECT e FROM Employee e where e.employeeId=?1")
    Employee findId(Integer id);

    @Modifying
    @Query(value = "DELETE r and e FROM employees_roles r INNER JOIN employee e ON r.user_id = e.employee_id" +
            " WHERE e.employee_id=?1  ",nativeQuery = true)
    public void deleteId(Integer id);

//    @Modifying
//    @Query(value = "DELETE r  FROM employee e INNER JOIN employees_roles r ON r.user_id = e.employee_id" +
//            " WHERE r.user_id=?1  ",nativeQuery = true)

     @Transactional
     public void deleteByEmployeeId(int id);



}
