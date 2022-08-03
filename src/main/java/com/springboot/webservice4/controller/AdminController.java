package com.springboot.webservice4.controller;

import com.springboot.webservice4.service.UserService;
import com.springboot.webservice4.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private SessionUtils sessionUtils;

    @GetMapping("/users")
    public String userList(Model model) {
        model.addAttribute("users", userService.allUsers());
        return "admin";
    }

    @DeleteMapping("/users")
    public String deleteUser(@RequestParam(required = true, defaultValue = "") Long userId) {
        String username = userService.findUserById(userId).getUsername();
        userService.deleteUser(userId);
        sessionUtils.expireUserSessions(username);
        return "admin";
    }

    @PatchMapping("/users")
    public String blockUser(@RequestParam(required = true, defaultValue = "") Long userId,
                            @RequestParam(required = true, defaultValue = "") Boolean isBlocked) {
        if (isBlocked) {
            userService.blockUser(userId);
            sessionUtils.expireUserSessions(userService.findUserById(userId).getUsername());
        } else {
            userService.unBlockUser(userId);
        }
        return "admin";
    }
}
