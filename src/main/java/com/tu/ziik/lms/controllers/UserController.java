package com.tu.ziik.lms.controllers;

import com.tu.ziik.lms.model.Role;
import com.tu.ziik.lms.repository.RoleRepository;
import com.tu.ziik.lms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tu.ziik.lms.model.User;
import com.tu.ziik.lms.service.SecurityService;
import com.tu.ziik.lms.service.UserService;
import com.tu.ziik.lms.validator.UserValidator;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;


    UserDetails details;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String listUsers(Model model) {



        model.addAttribute("users", userRepository.findAll());
        return "user-list";
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public String registerUser(Model model) {
        model.addAttribute("userForm", new User());

        model.addAttribute("roles", roleRepository.findAll());
        return "register";
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {

        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roleRepository.findAll());
            return "register";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }


    //    @RequestMapping(value = "/registration", method = RequestMethod.GET)
//    public String registration(Model model) {
//        model.addAttribute("userForm", new User());
//
//        List<java.lang.String> roles = new ArrayList<>();
//
//        List<Role> roleList = roleRepository.findAll();
//
//        for (Role currentRole: roleList){
//            roles.add(currentRole.getName());
//        }
//
//        model.addAttribute("roles", roleList);
//
//        return "registration";
//    }


//    @RequestMapping(value = "/registration", method = RequestMethod.POST)
//    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
//        userValidator.validate(userForm, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            return "registration";
//        }
//
//        userService.save(userForm);
//
//        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
//
//        return "redirect:/welcome";
//    }
}
