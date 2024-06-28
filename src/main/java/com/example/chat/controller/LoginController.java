package com.example.chat.controller;

import com.example.chat.entity.User;
import com.example.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/chat")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public boolean login(@RequestBody User user) {
        User userExist = userService.checkUser(user.getUserName(), user.getPassword());
        return userExist != null;
    }
}
