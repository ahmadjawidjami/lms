package com.tu.ziik.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tu.ziik.lms.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
