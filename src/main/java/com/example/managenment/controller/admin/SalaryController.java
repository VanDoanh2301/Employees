package com.example.managenment.controller.admin;

import com.example.managenment.domain.Department;
import com.example.managenment.domain.Employee;
import com.example.managenment.domain.Salary;
import com.example.managenment.model.DepartmentDto;
import com.example.managenment.model.SalaryDto;
import com.example.managenment.repository.EmployeeRepository;
import com.example.managenment.repository.SalaryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/salaries")
public class SalaryController {
    @Autowired
    private SalaryRepository salaryRepo;

    @Autowired
    private EmployeeRepository employeeRepo;

    @GetMapping("/add/{employeeId}")
    public String add(Model model, @PathVariable("employeeId") Integer employeeId) {
        Employee employee =   employeeRepo.findId(employeeId);
        model.addAttribute("emp",employee);
        model.addAttribute("salary",new SalaryDto());

        return "admin/salaries/addOrEdit";
    }
    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate(ModelMap model, @ModelAttribute("department") SalaryDto salaryDto) {

        Salary entity = new Salary();
        BeanUtils.copyProperties(salaryDto,entity);
        salaryRepo.save(entity);
        model.addAttribute("message","Department is save");
        return "redirect:/admin/salaries";
    }
    @GetMapping("")
    public  String viewSalary(Model model) {
        List<Salary> salaries = salaryRepo.findAll();
        model.addAttribute("salaries",salaries);
        return "admin/salaries/list";
    }


}
