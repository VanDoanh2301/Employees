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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

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
//@PreAuthorize("hasAnyAuthority('EDIT_EMPLOYEE')")

    public ModelAndView edit(ModelMap model, @PathVariable("employeeId") Integer employeeId) {
        Optional<Employee> op = employeeService.findById(employeeId);
        EmployeeDto employeeDto = new EmployeeDto();
        if(op.isPresent()) {
            Employee employee = op.get();
            BeanUtils.copyProperties(employee,employeeDto);
            employeeDto.setEdit(true);
            model.addAttribute("employee",employeeDto);
            List<Department> departments=  departmentService.findAll();
            model.addAttribute("departments",departments);
            return new ModelAndView("/admin/employees/addOrEdit",model);
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
    public ModelAndView saveOrUpdate(ModelMap model, @ModelAttribute("department") EmployeeDto employeeDto, BindingResult result) {
        if(employeeService.existsByName(employeeDto.getName())){
            model.addAttribute("m","Name is variable");
            return new ModelAndView("/admin/employees/addOrEdit",model);
        }
        if(employeeService.existsByEmail(employeeDto.getEmail())){
            model.addAttribute("m","Email is variable");
            return new ModelAndView("/admin/employees/addOrEdit",model);
        }
        employeeDto.setPassword(encoder().encode(employeeDto.getPassword()));
        Employee entity = new Employee();
        BeanUtils.copyProperties(employeeDto,entity);
        employeeService.save(entity);
        model.addAttribute("message","employees is save");
        return new ModelAndView("forward:/admin/employees",model);
    }
    @RequestMapping("")
    public String list(ModelMap model) {
        List<Employee> employees=  employeeService.findAll();
        model.addAttribute("employees",employees);
        return "admin/employees/list";
    }
}
