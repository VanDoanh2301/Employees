package com.example.managenment.controller.admin;

import com.example.managenment.domain.Department;
import com.example.managenment.domain.Employee;
import com.example.managenment.domain.Role;
import com.example.managenment.model.EmployeeDto;
import com.example.managenment.repository.RoleRepository;
import com.example.managenment.service.DepartmentService;
import com.example.managenment.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private RoleRepository roleRepos;

    private PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @GetMapping("/add")
    public String add(Model model) {
        List<Department> departments=  departmentService.findAll();
        List<Role> roles = roleRepos.findAll();
        model.addAttribute("departments",departments);
        model.addAttribute("employee",new EmployeeDto());
        model.addAttribute("listRole",roles);
        return "admin/employees/addOrEdit";
    }
@GetMapping("/edit/{employeeId}")
@PreAuthorize("hasAnyAuthority('EDIT_EMPLOYEE')")
    public ModelAndView edit(ModelMap model, @PathVariable("employeeId") Integer employeeId) {
        Optional<Employee> op = employeeService.findById(employeeId);
        EmployeeDto employeeDto = new EmployeeDto();
        if(op.isPresent()) {
            Employee employee = op.get();
            BeanUtils.copyProperties(employee,employeeDto);
            employeeDto.setEdit(true);
            model.addAttribute("employee",employeeDto);
            List<Department> departments=  departmentService.findAll();
            List<Role> roles = roleRepos.findAll();
            model.addAttribute("departments",departments);
            model.addAttribute("listRole",roles);
            return new ModelAndView("/admin/employees/updateEmployee",model);
        }
        model.addAttribute("message","Employee is null");

        return new ModelAndView("forward:/admin/employees",model);
    }
    @GetMapping("/delete/{employeeId}")
    public String delete(Model model,@PathVariable("employeeId") Integer employeeId) {
        employeeService.deleteByEmployeeId(employeeId);
        model.addAttribute("m","department is deleted");
        return "forward:/admin/employees";
    }
    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate(ModelMap model, @ModelAttribute("department") EmployeeDto employeeDto) {
        if(employeeService.existsByName(employeeDto.getName())){
            model.addAttribute("m","Name is variable");
            return "/admin/employees/addOrEdit";
        }
        if(employeeService.existsByEmail(employeeDto.getEmail())){
            model.addAttribute("m","Email is variable");
            return "/admin/employees/addOrEdit";
        }
        employeeDto.setPassword(encoder().encode(employeeDto.getPassword()));
        Employee entity = new Employee();
        BeanUtils.copyProperties(employeeDto,entity);
        employeeService.save(entity);
        model.addAttribute("message","employees is save");
        return "redirect:/admin/employees";
    }
    @PostMapping("/updateEmployee")
    public String Update(ModelMap model, @ModelAttribute("department") EmployeeDto employeeDto) {
        employeeDto.setPassword(encoder().encode(employeeDto.getPassword()));
        Employee entity = employeeService.findId(employeeDto.getEmployeeId());
        BeanUtils.copyProperties(employeeDto,entity);
        employeeService.save(entity);
        model.addAttribute("message","employees is save");
        return "redirect:/admin/employees";
    }
//    @RequestMapping("")
//    public String list(ModelMap model) {
//        List<Employee> employees=  employeeService.findAll();
//        model.addAttribute("employees",employees);
//        return "admin/employees/list";
//    }
@GetMapping("")
public String seacrch(ModelMap model
        ,@RequestParam(value = "name",required = false) String name
        ,@RequestParam(value = "page") Optional<Integer> page
        ,@RequestParam("size") Optional<Integer> size) {

    int currentPage = page.orElse(0);
    int pageSize = size.orElse(3);

    Pageable pageable = PageRequest.of(currentPage,pageSize, Sort.by("name"));

    Page<Employee> resultPage= null;
    if(StringUtils.hasText(name)) {
        resultPage =employeeService.findByNameContaining(name,pageable);
        model.addAttribute("name",name);
    } else {
        resultPage = employeeService.findAll(pageable);
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

    model.addAttribute("employeePage",resultPage);
    return "admin/employees/list";
}
}
