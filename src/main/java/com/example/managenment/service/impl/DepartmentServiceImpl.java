package com.example.managenment.service.impl;

import com.example.managenment.domain.Department;
import com.example.managenment.repository.DepartmentRepository;
import com.example.managenment.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepos;

    @Override
    @Deprecated
    public Department getById(Integer integer) {
        return departmentRepos.getById(integer);
    }

    @Override
    public Department getReferenceById(Integer integer) {
        return departmentRepos.getReferenceById(integer);
    }

    @Override
    public <S extends Department> List<S> saveAll(Iterable<S> entities) {
        return departmentRepos.saveAll(entities);
    }

    @Override
    public List<Department> findAll() {
        return departmentRepos.findAll();
    }

    @Override
    public List<Department> findAllById(Iterable<Integer> integers) {
        return departmentRepos.findAllById(integers);
    }

    @Override
    public <S extends Department> S save(S entity) {
        return departmentRepos.save(entity);
    }

    @Override
    public Optional<Department> findById(Integer integer) {
        return departmentRepos.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return departmentRepos.existsById(integer);
    }

    @Override
    public long count() {
        return departmentRepos.count();
    }

    @Override
    public void deleteById(Integer integer) {
        departmentRepos.deleteById(integer);
    }

    @Override
    public void delete(Department entity) {
        departmentRepos.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        departmentRepos.deleteAllById(integers);
    }

    @Override
    public void deleteAll(Iterable<? extends Department> entities) {
        departmentRepos.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        departmentRepos.deleteAll();
    }

    @Override
    public List<Department> findAll(Sort sort) {
        return departmentRepos.findAll(sort);
    }

    @Override
    public Page<Department> findAll(Pageable pageable) {
        return departmentRepos.findAll(pageable);
    }

    @Override
    public boolean existsByName(String name) {
        return departmentRepos.existsByName(name);
    }


    @Override
    public Page<Department> findByNameContaining(String name, Pageable pageable) {
        return departmentRepos.findByNameContaining(name, pageable);
    }

}
