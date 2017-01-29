package com.tu.ziik.lms.controllers;

import com.tu.ziik.lms.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.tu.ziik.lms.model.User;
import com.tu.ziik.lms.service.SecurityService;
import com.tu.ziik.lms.service.UserService;
import com.tu.ziik.lms.validator.UserValidator;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String listUsers(Model model) {

        model.addAttribute("users", userService.findAllUsers());
        return "user-list";
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public String registerUser(Model model) {

        User userForm = (User) model.asMap().get("userForm");

        if (userForm == null) {
            model.addAttribute("userForm", new User());
        }else
            model.addAttribute("userForm", userForm);

        model.addAttribute("roles", roleRepository.findAll());
        boolean isAuthenticatedAsAdmin = securityService.isUserAuthenticatedAsAdmin();
        if (isAuthenticatedAsAdmin){
            model.addAttribute("pageTitle", "Register User");
            return "register";
        }

        model.addAttribute("setRegisterActive", "true");
        return "new-login";
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("userForm") User userForm, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userForm", userForm);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userForm", bindingResult);

            if (userForm.getId() != null){
//                redirectAttributes.addAttribute("id", userForm.getId());
//                return "redirect:/user/edit/{id}";
                return "redirect:/user/edit/" + userForm.getId() + "";
            }

            return "redirect:/user/register";
        }

        userService.save(userForm);

        if (securityService.isUserAuthenticatedAsAdmin()){
            return "redirect:/users";
        }

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "user/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable Long id) {

        userService.delete(id);
        return "redirect:/users";
    }


    @RequestMapping(value = "/user/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {

        User userForm = (User) model.asMap().get("userForm");

        if (userForm == null) {
              User user = userService.findById(id);
              model.addAttribute("userForm", user);
           // model.addAttribute("userForm", new User());
        }else
            model.addAttribute("userForm", userForm);

      //  User user = userService.findById(id);

      //  model.addAttribute("userForm", user);
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("pageTitle", "Edit User");

        return "register";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        model.addAttribute("userForm", new User());
        if (error != null)
            model.addAttribute("loginInvalidMessage", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("logoutMessage", "You have been logged out successfully.");

        return "new-login";
    }

    @RequestMapping(value = "/user/forgot", method = RequestMethod.GET)
    public String showForgetForm() {


        return "forgot";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }

}
