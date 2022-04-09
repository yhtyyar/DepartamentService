package com.example.DepartmentService.service;

import com.example.DepartmentService.model.Department;

import java.util.List;

public interface DepartmentService {

    Department create(Department department);
    Department update(Department department);
    Department getById(Long id);
    void deleteById(Long id);
    List<Department> getAll();
}
