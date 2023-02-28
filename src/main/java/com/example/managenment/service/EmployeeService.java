package com.example.managenment.service;

import com.example.managenment.domain.Employee;
import com.example.managenment.model.EmployeeDto;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public interface EmployeeService {


    void flush();

    <S extends Employee> S saveAndFlush(S entity);

    <S extends Employee> List<S> saveAllAndFlush(Iterable<S> entities);

    @Deprecated
    void deleteInBatch(Iterable<Employee> entities);

    void deleteAllInBatch(Iterable<Employee> entities);

    void deleteAllByIdInBatch(Iterable<Integer> integers);

    void deleteAllInBatch();

    @Deprecated
    Employee getOne(Integer integer);

    @Deprecated
    Employee getById(Integer integer);

    Employee getReferenceById(Integer integer);

    <S extends Employee> List<S> findAll(Example<S> example);

    <S extends Employee> List<S> findAll(Example<S> example, Sort sort);

    <S extends Employee> List<S> saveAll(Iterable<S> entities);

    List<Employee> findAll();

    List<Employee> findAllById(Iterable<Integer> integers);

    <S extends Employee> S save(S entity);

    Optional<Employee> findById(Integer integer);

    boolean existsById(Integer integer);

    long count();

    void deleteById(Integer integer);

    void delete(Employee entity);

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteAll(Iterable<? extends Employee> entities);

    void deleteAll();

    List<Employee> findAll(Sort sort);

    Page<Employee> findAll(Pageable pageable);

    <S extends Employee> Optional<S> findOne(Example<S> example);

    <S extends Employee> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends Employee> long count(Example<S> example);

    <S extends Employee> boolean exists(Example<S> example);

    <S extends Employee, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    boolean existsByName(String name);

    boolean existsByEmail(String email);


    Page<Employee> getByDepartmentId(Integer id, Pageable pageable);

    Employee findByEmail(String email);

    Employee getByName(String username);

    Employee findId(Integer id);

    void deleteId(Integer id);

    void deleteByEmployeeId(int id);

    Page<Employee> findByNameContaining(String name, Pageable pageable);

//    Employee saveEmployee(EmployeeDto employeeDto);
}
