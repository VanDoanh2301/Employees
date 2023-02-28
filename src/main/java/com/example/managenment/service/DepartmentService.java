package com.example.managenment.service;

import com.example.managenment.domain.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DepartmentService {
    @Deprecated
    Department getById(Integer integer);

    Department getReferenceById(Integer integer);

    <S extends Department> List<S> saveAll(Iterable<S> entities);

    List<Department> findAll();

    List<Department> findAllById(Iterable<Integer> integers);

    <S extends Department> S save(S entity);

    Optional<Department> findById(Integer integer);

    boolean existsById(Integer integer);

    long count();

    void deleteById(Integer integer);

    void delete(Department entity);

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteAll(Iterable<? extends Department> entities);

    void deleteAll();

    List<Department> findAll(Sort sort);

    Page<Department> findAll(Pageable pageable);

    public  boolean existsByName(String name);


    Page<Department> findByNameContaining(String name, Pageable pageable);


}
