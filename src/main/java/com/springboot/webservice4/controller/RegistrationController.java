package com.springboot.webservice4.controller;

import com.springboot.webservice4.entity.User;
import com.springboot.webservice4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/signup")
    public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (userForm.getPassword().isEmpty()) {
            model.addAttribute("passwordError");
            return "registration";
        }
        if (!userService.saveUser(userForm)) {
            model.addAttribute("usernameError");
            return "registration";
        }
        return "redirect:/signin";
    }
}
