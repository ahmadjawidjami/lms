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
import java.util.Set;

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

        Set<Role> theRoles = new HashSet<>();

        if (user.getTheRoles() != null && !user.getTheRoles().isEmpty()) {
            for (String currentRole : user.getTheRoles()) {
                theRoles.addAll(roleRepository.findByName(currentRole));
            }

        }else {
            theRoles.addAll(roleRepository.findByName("ROLE_USER"));
        }


        //user.setRoles(new HashSet<>(roleRepository.findByName("ROLE_USER")));
        user.setRoles(theRoles);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findOne(id);
    }
}
