package com.firstmicroservice.department.controller;

import com.firstmicroservice.department.entity.Department;
import com.firstmicroservice.department.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/")
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department) {
        log.info("Inside saveDepartment Method of DepartmentController");
        return new ResponseEntity<>(departmentService.saveDepartment(department), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Department>> getAllDepartments() {
        log.info("Inside getAllDepartments Method of DepartmentController");
        List<Department> departments = departmentService.getAllDepartments();
        return new ResponseEntity<List<Department>>(departments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> findDepartmentById(@PathVariable("id") Long departmentId) {
        log.info("Inside findDepartmentById Method of DepartmentController");
        return new ResponseEntity<>(departmentService.findDepartmentById(departmentId), HttpStatus.OK);
    }

}
