package com.firstmicroservice.department.service;

import com.firstmicroservice.department.entity.Department;
import com.firstmicroservice.department.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public Department saveDepartment(Department department) {
        log.info("Inside saveDepartment Method of DepartmentService");
        return departmentRepository.save(department);
    }

    public Department findDepartmentById(Long departmentId) {
        log.info("Inside saveDepartment Method of DepartmentService");
        return departmentRepository.findByDepartmentId(departmentId);
    }

    public List<Department> getAllDepartments() {
        log.info("Inside getAllDepartments Method of DepartmentService");
        return departmentRepository.findAll();
    }

}
