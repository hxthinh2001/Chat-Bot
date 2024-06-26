package com.example.chat.controller;

import com.example.chat.entity.User;
import com.example.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public List<User> search(@RequestParam String username) {
        List<User> users = userService.findByUsername(username);
        if (users.isEmpty()) {
            return null;
        } else {
            return users;
        }
    }

}
