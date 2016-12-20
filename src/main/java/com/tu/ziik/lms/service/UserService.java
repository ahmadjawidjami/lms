package com.tu.ziik.lms.service;

import com.tu.ziik.lms.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
