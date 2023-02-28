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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
//    @GetMapping("")
//    public  String viewSalary(Model model) {
//        List<Salary> salaries = salaryRepo.findAll();
//        model.addAttribute("salaries",salaries);
//        return "admin/salaries/list";
//    }
    @GetMapping("")
    public String seacrch(ModelMap model
            ,@RequestParam(value = "month",required = false) Integer month
            ,@RequestParam(value = "page") Optional<Integer> page
            ,@RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(0);
        int pageSize = size.orElse(5);

        Pageable pageable = PageRequest.of(currentPage,pageSize);

        Page<Salary> resultPage= null;
        if(month != null) {
            resultPage =salaryRepo.findAllByMonth(month,pageable);
            model.addAttribute("month",month);
        } else {
            resultPage = salaryRepo.findAll(pageable);
        }
        int totalPage = resultPage.getTotalPages();
        if(totalPage > 0) {
            int start = Math.max(0,currentPage - 2);
            int end = Math.min(currentPage + 2,totalPage-1);
            if(totalPage > 5) {
                if(end == totalPage) start = end - 4;
                else if(start ==1) end = start + 4;
            }
            List<Integer> pageNumbers = IntStream.rangeClosed(start,end).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers",pageNumbers);
        }

        model.addAttribute("salaryPage",resultPage);
        return "admin/salaries/list";
    }


}
