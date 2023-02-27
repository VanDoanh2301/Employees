package com.example.managenment;

import com.example.managenment.domain.Department;
import com.example.managenment.repository.DepartmentRepository;
import com.example.managenment.repository.EmployeeRepository;
import com.example.managenment.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootTest
class ManagenmentApplicationTests {
    @Autowired
    private DepartmentRepository categoryRepo;

   @Autowired
   private EmployeeRepository repo;

    @Test
    void contextLoads() {
        repo.findById(2);
    }

}
