package com.example.managenment.payload;

import com.example.managenment.domain.Department;
import com.example.managenment.domain.Employee;
import com.example.managenment.domain.Privilege;
import com.example.managenment.domain.Role;
import com.example.managenment.repository.DepartmentRepository;
import com.example.managenment.repository.EmployeeRepository;
import com.example.managenment.repository.PrivilegeRepository;
import com.example.managenment.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.naming.Name;
import java.util.Arrays;
import java.util.List;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    boolean alreadySetup =true;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private PrivilegeRepository privilegeRepo;
    @Autowired
    private EmployeeRepository userRepo;
    private PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private DepartmentRepository departmentRepo;

    @Autowired
    private EmployeeRepository employeeRepo;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Privilege admin
                = createPrivilegeIfNotFound("ADMIN_ALL");
        Privilege readUser
                = createPrivilegeIfNotFound("EMPLOYEE_READ");
        Privilege signin
                = createPrivilegeIfNotFound("EMPLOYEE_SIGNIN");
        Privilege signup
                = createPrivilegeIfNotFound("EMPLOYEE_SIGNUP");
        Privilege remove
                = createPrivilegeIfNotFound("REMOVE_EMPLOYEE");
        Privilege edit
                = createPrivilegeIfNotFound("EDIT_EMPLOYEE");
        Privilege add
                = createPrivilegeIfNotFound("ADD_EMPLOYEE");


        List<Privilege> empl = Arrays.asList(readUser,signin,signup);
        List<Privilege> mana = Arrays.asList(readUser,signin,signup,remove,add);
        List<Privilege> adm = Arrays.asList(readUser,signin,signup,remove,add,admin,edit);

        Role admi = createRoleIfNotFound("ADMIN",adm );
        Role employee = createRoleIfNotFound("EMPLOYEE",empl);
        Role manager = createRoleIfNotFound("MANAGER", mana);

        Department GD = createDepartmentIfNotFound(1,"GD", "PHONG GIAM DOC");
        Department NS = createDepartmentIfNotFound(2,"NS", "PHONG NHAN SU");
        Department P1 = createDepartmentIfNotFound(3,"P1", "PHONG PHONG 1");
        Department P2 = createDepartmentIfNotFound(4,"P2", "PHONG PHONG 2");

        Employee Admin = Employee.builder()
                .employeeId(1)
                .name("Admin")
                .email("Admin@gmail.com")
                .password(encoder().encode("123"))
                .phone(123)
                .address("HA NOI")
                .department(GD)
                .roles(Arrays.asList(admi))
                .build();
        employeeRepo.save(Admin);
//
//        Collection<Role> roles = user.getRoles();
//        Collection<Privilege> privileges = Arrays.asList(admin,readUser,signin,signup,remove);
//        roles.forEach(role -> {
//            role.setPrivileges(privileges);
//            roleRepo.save(role);
//        });

//        List<Privilege> adminPrivileges = Arrays.asList(
//               removeUser, readUser,signin);
//        createRoleIfNotFound("ADMIN",adminPrivileges);
//        createRoleIfNotFound("USER",Arrays.asList(readUser));
        alreadySetup = true;
    }
    @Transactional
        //Khoi tao privilege neu ko tim thay tien hanh tao
    Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege = privilegeRepo.findByName(name);
        if(privilege == null) {
            privilege = new Privilege(name);
            privilegeRepo.save(privilege);
        }
        return privilege;
    }
    @Transactional
        //Khoi tao Role neu ko tim thay tien hanh tao
    Role createRoleIfNotFound(String name, List<Privilege> privilege) {
        Role role = roleRepo.findByName(name);
        if(role == null) {
            role = new Role(name);
            role.setPrivileges(privilege);
            roleRepo.save(role);
        }
        return role;
    }
    @Transactional
    Department createDepartmentIfNotFound(Integer id, String shortName, String name) {
        Department department = departmentRepo.findByShortName(shortName);
        if(department == null) {
            department = new Department(id, shortName, name);
            departmentRepo.save(department);
        }
        return department;
    }

}