package com.example.managenment.service.impl;

import com.example.managenment.domain.Employee;
import com.example.managenment.domain.Role;
import com.example.managenment.model.EmployeeDto;
import com.example.managenment.repository.EmployeeRepository;
import com.example.managenment.repository.RoleRepository;
import com.example.managenment.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepos;

    @Autowired
    private RoleRepository RoleRepo;

    @Override
    public void flush() {
        employeeRepos.flush();
    }

    @Override
    public <S extends Employee> S saveAndFlush(S entity) {
        return employeeRepos.saveAndFlush(entity);
    }

    @Override
    public <S extends Employee> List<S> saveAllAndFlush(Iterable<S> entities) {
        return employeeRepos.saveAllAndFlush(entities);
    }

    @Override
    @Deprecated
    public void deleteInBatch(Iterable<Employee> entities) {
        employeeRepos.deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch(Iterable<Employee> entities) {
        employeeRepos.deleteAllInBatch(entities);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {
        employeeRepos.deleteAllByIdInBatch(integers);
    }

    @Override
    public void deleteAllInBatch() {
        employeeRepos.deleteAllInBatch();
    }

    @Override
    @Deprecated
    public Employee getOne(Integer integer) {
        return employeeRepos.getOne(integer);
    }

    @Override
    @Deprecated
    public Employee getById(Integer integer) {
        return employeeRepos.getById(integer);
    }

    @Override
    public Employee getReferenceById(Integer integer) {
        return employeeRepos.getReferenceById(integer);
    }

    @Override
    public <S extends Employee> List<S> findAll(Example<S> example) {
        return employeeRepos.findAll(example);
    }

    @Override
    public <S extends Employee> List<S> findAll(Example<S> example, Sort sort) {
        return employeeRepos.findAll(example, sort);
    }

    @Override
    public <S extends Employee> List<S> saveAll(Iterable<S> entities) {
        return employeeRepos.saveAll(entities);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepos.findAll();
    }

    @Override
    public List<Employee> findAllById(Iterable<Integer> integers) {
        return employeeRepos.findAllById(integers);
    }

    @Override
    public <S extends Employee> S save(S entity) {
        return employeeRepos.save(entity);
    }

    @Override
    public Optional<Employee> findById(Integer integer) {
        return employeeRepos.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return employeeRepos.existsById(integer);
    }

    @Override
    public long count() {
        return employeeRepos.count();
    }

    @Override
    public void deleteById(Integer integer) {
        employeeRepos.deleteById(integer);
    }

    @Override
    public void delete(Employee entity) {
        employeeRepos.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        employeeRepos.deleteAllById(integers);
    }

    @Override
    public void deleteAll(Iterable<? extends Employee> entities) {
        employeeRepos.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        employeeRepos.deleteAll();
    }

    @Override
    public List<Employee> findAll(Sort sort) {
        return employeeRepos.findAll(sort);
    }

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepos.findAll(pageable);
    }

    @Override
    public <S extends Employee> Optional<S> findOne(Example<S> example) {
        return employeeRepos.findOne(example);
    }

    @Override
    public <S extends Employee> Page<S> findAll(Example<S> example, Pageable pageable) {
        return employeeRepos.findAll(example, pageable);
    }

    @Override
    public <S extends Employee> long count(Example<S> example) {
        return employeeRepos.count(example);
    }

    @Override
    public <S extends Employee> boolean exists(Example<S> example) {
        return employeeRepos.exists(example);
    }

    @Override
    public <S extends Employee, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return employeeRepos.findBy(example, queryFunction);
    }

    @Override
    public boolean existsByName(String name) {
        return employeeRepos.existsByName(name);
    }

    @Override
    public boolean existsByEmail(String email) {
        return employeeRepos.existsByEmail(email);
    }

    @Override
    public List<Employee> getByDepartmentId(Integer id) {
        return employeeRepos.getByDepartmentId(id);

    }
//    @Override
//    public Employee saveEmployee(EmployeeDto employeeDto) {
//        List<String> roleDto = employeeDto.getRoles();
//        List<Role> listRole = new ArrayList<>();
//        roleDto.forEach(ro -> {
//            switch (ro) {
//                case "user": {
//                    Role userRole = RoleRepo.findByName("USER");
//                    if (userRole == null) {
//                        userRole = new Role("USER");
//                        RoleRepo.save(userRole);
//                    }
//                    listRole.add(userRole);
//                    break;
//                }
//                case "manager": {
//                    Role managerRole = RoleRepo.findByName("MANAGER");
//                    if (managerRole == null) {
//                        managerRole = new Role("MANAGER");
//                        RoleRepo.save(managerRole);
//                    }
//                    listRole.add(managerRole);
//                    break;
//                }
//                case "admin": {
//                    Role managerRole = RoleRepo.findByName("ADMIN");
//                    if (managerRole == null) {
//                        managerRole = new Role("ADMIN");
//                        RoleRepo.save(managerRole);
//                    }
//                    listRole.add(managerRole);
//                    break;
//                }
//            }
//        });
//
//        Employee employee = Employee.builder()
//                .employeeId(employeeDto.getEmployeeId())
//                .name(employeeDto.getName())
//                .email(employeeDto.getEmail())
//                .password(employeeDto.getPassword())
//                .phone(employeeDto.getPhone())
//                .department(employeeDto.getDepartment())
//                .roles(listRole)
//                .build();
////         List<Role> roles = user.getRoles();
////        Collection<Privilege> privileges = new ArrayList<>();
////        privileges.add(privilege);
////        privileges.add(privilege1);
////        roles.forEach(role -> {
////            role.setPrivileges(privileges);
////        });
//
//        return employeeRepos.save(employee);
//
//    }

}
