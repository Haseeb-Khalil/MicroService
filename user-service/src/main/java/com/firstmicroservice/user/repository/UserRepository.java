package com.firstmicroservice.user.repository;

import com.firstmicroservice.user.entity.DepartmentUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<DepartmentUser, Long> {
    DepartmentUser findByUserId(Long userId);
}
