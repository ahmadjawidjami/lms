package com.tu.ziik.lms.service;

import com.tu.ziik.lms.model.User;

import java.util.List;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    void delete(Long id);

    List<User> findAllUsers();

    User findById(Long id);
}
