package com.tu.ziik.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tu.ziik.lms.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
