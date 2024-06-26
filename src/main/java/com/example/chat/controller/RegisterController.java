package com.example.chat.controller;

import com.example.chat.entity.User;
import com.example.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/demo")
public class RegisterController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public User createUser(@RequestBody User user) {
        List<User> exitUser = userService.checkUserExist(user.getUserName(), user.getEmail());
        if (!exitUser.isEmpty()) {
            return exitUser.get(0);
        } else {
            return userService.createUser(user);
        }

    }
}
