package com.example.managenment.repository;

import com.example.managenment.domain.Department;
import com.example.managenment.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {
         public  boolean existsByName(String name);
         public  boolean existsByShortName(String shortName);
         List<Department> findByNameContaining(String name);

         Page<Department> findByNameContaining(String name, Pageable pageable);

         public  Department findByShortName(String shortName);

    @Query("SELECT e FROM Department e where e.departmentId=?1")
    Department findId(Integer id);
}
