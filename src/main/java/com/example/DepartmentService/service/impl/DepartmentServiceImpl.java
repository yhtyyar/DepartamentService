package com.example.DepartmentService.service.impl;

import com.example.DepartmentService.model.Department;
import com.example.DepartmentService.repository.DepartmentRepository;
import com.example.DepartmentService.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {


    @Autowired
    private final DepartmentRepository departmentRepository;

    @Override
    public Department create(Department department) {
        log.info("Create new Department{}", department);

        Department anotherDepartment = new Department();
        anotherDepartment.setEmail(department.getEmail());
        //password encoding
        anotherDepartment.setPassword(BCrypt.hashpw(department.getPassword(), BCrypt.gensalt(12)));
        anotherDepartment.setFirstName(department.getFirstName());
        anotherDepartment.setLastName(department.getLastName());
        anotherDepartment.setRole(department.getRole());

        return departmentRepository.save(anotherDepartment);
    }

    @Override
    public Department update(Department department) {
        log.info("Update Department{}", department);

        Department anotherDepartment = new Department();
        anotherDepartment.setEmail(department.getEmail());
        //password encoding
        anotherDepartment.setPassword(BCrypt.hashpw(department.getPassword(), BCrypt.gensalt(12)));
        anotherDepartment.setFirstName(department.getFirstName());
        anotherDepartment.setLastName(department.getLastName());
        anotherDepartment.setRole(department.getRole());

        return departmentRepository.save(anotherDepartment);
    }

    @Override
    public Department getById(Long id) {
        log.info("Get Department by id {}", id);
        return departmentRepository.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        log.info("Delete Department by id {}", id);
        departmentRepository.deleteById(id);
    }

    @Override
    public List<Department> getAll() {
        log.info("Get all Departments");
        return departmentRepository.findAll();
    }
}
