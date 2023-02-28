package com.example.managenment.controller.admin;

import com.example.managenment.domain.Department;
import com.example.managenment.domain.Employee;
import com.example.managenment.model.DepartmentDto;
import com.example.managenment.service.DepartmentService;
import com.example.managenment.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
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
@Slf4j
@Controller
@RequestMapping( "/admin/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("department",new DepartmentDto());

        return "admin/departments/addOrEdit";
    }
    @GetMapping("/edit/{departmentId}")
    public ModelAndView edit(ModelMap model, @PathVariable("departmentId") Integer departmentId) {
        Optional<Department> op = departmentService.findById(departmentId);
        DepartmentDto departmentDto = new DepartmentDto();
        if(op.isPresent()) {
            Department department = op.get();
            BeanUtils.copyProperties(department,departmentDto);
            departmentDto.setEdit(true);
            model.addAttribute("department",departmentDto);
            return new ModelAndView("/admin/departments/addOrEdit",model);
        }
         model.addAttribute("message","Department is null");

        return new ModelAndView("forward:/admin/departments",model);
    }
    @GetMapping("/delete/{departmentId}")
    public ModelAndView delete(ModelMap model,@PathVariable("departmentId") Integer departmentId) {
        departmentService.deleteById(departmentId);
        model.addAttribute("m","department is deleted");
        return new ModelAndView("forward:/admin/departments",model);
    }
    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate(ModelMap model, @ModelAttribute("department") DepartmentDto departmentDto) {
        if(departmentService.existsByName(departmentDto.getName())){
            model.addAttribute("m","Name is variable");
            return "/admin/departments/addOrEdit";
        }
        if(departmentDto.getDepartmentId() == 0) {
            model.addAttribute("m","Not 0");
            return "/admin/departments/addOrEdit";
        }
        Department entity = new Department();
        BeanUtils.copyProperties(departmentDto,entity);
        departmentService.save(entity);
        model.addAttribute("message","Department is save");
        return "redirect:/admin/departments";
    }
//    @RequestMapping("")
//    public String list(ModelMap model) {
//        List<Department> departments=  departmentService.findAll();
//        model.addAttribute("departments",departments);
//        return "admin/departments/list";
//    }
    @GetMapping("")
    public String seacrch(ModelMap model
            ,@RequestParam(value = "name",required = false) String name
            ,@RequestParam(value = "page") Optional<Integer> page
            ,@RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(0);
        int pageSize = size.orElse(3);

        Pageable pageable = PageRequest.of(currentPage,pageSize,Sort.by("name"));

        Page<Department> resultPage= null;
        if(StringUtils.hasText(name)) {
            resultPage =departmentService.findByNameContaining(name,pageable);
            model.addAttribute("name",name);
        } else {
            resultPage = departmentService.findAll(pageable);
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

        model.addAttribute("departmentPage",resultPage);
        return "admin/departments/list";
    }

    @GetMapping("/view/{departmentId}")
    public String view(Model model,@PathVariable("departmentId") Integer departmentId
            ,@RequestParam(value = "page") Optional<Integer> page
            ,@RequestParam("size") Optional<Integer> size
                       ) {
        int currentPage = page.orElse(0);
        int pageSize = size.orElse(3);
        Pageable pageable = PageRequest.of(currentPage,pageSize);


        Page<Employee> employeePage = employeeService.getByDepartmentId(departmentId,pageable);
        model.addAttribute("employeePage", employeePage);
        return "admin/employees/list";
    }
}
