package com.firstmicroservice.user.controller;

import com.firstmicroservice.user.VO.ResponseTemplateVO;
import com.firstmicroservice.user.entity.DepartmentUser;
import com.firstmicroservice.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public DepartmentUser saveUser(@RequestBody DepartmentUser departmentUser) {
        log.info("Inside saveUser Method of UserController");
        return userService.saveUser(departmentUser);
    }

    @GetMapping("/{id}")
    public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") Long userId) {
        log.info("Inside getUserWithDepartment Method of UserController");
        return userService.getUserWithDepartment(userId);
    }
    @GetMapping("/all")
    public ResponseEntity<List<DepartmentUser>> getAllUsers() {
        log.info("Inside getAllUsers Method of DepartmentController");
        List<DepartmentUser> users = userService.getAllUsers();
        return new ResponseEntity<List<DepartmentUser>>(users, HttpStatus.OK);
    }
}
