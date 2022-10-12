package com.firstmicroservice.user.service;

import com.firstmicroservice.user.VO.Department;
import com.firstmicroservice.user.VO.ResponseTemplateVO;
import com.firstmicroservice.user.entity.DepartmentUser;
import com.firstmicroservice.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

    public DepartmentUser saveUser(DepartmentUser departmentUser) {
        log.info("Inside saveUser Method of UserService");
        return userRepository.save(departmentUser);
    }

    public List<DepartmentUser> getAllUsers() {
        log.info("Inside getAllUsers Method of UserService");
        return userRepository.findAll();
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        log.info("Inside getUserWithDepartment Method of UserService");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        DepartmentUser departmentUser = userRepository.findByUserId(userId);
        Department department =
                restTemplate.getForObject("http://localhost:9001/departments/" + departmentUser.getDepartmentId(), Department.class);
        vo.setDepartmentUser(departmentUser);
        vo.setDepartment(department);
        return vo;
    }

}
