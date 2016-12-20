package com.tu.ziik.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tu.ziik.lms.model.Role;
import com.tu.ziik.lms.model.User;
import com.tu.ziik.lms.repository.RoleRepository;
import com.tu.ziik.lms.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        
        List<Role> roles = new ArrayList<>();
        
        Role admin = new Role();
        admin.setName("ROLE_ADMIN");
        
        roles.add(admin);
        
        user.setRoles(new HashSet<>(roleRepository.findByName("ROLE_USER")));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
