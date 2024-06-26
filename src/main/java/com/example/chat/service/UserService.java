package com.example.chat.service;

import com.example.chat.entity.FriendRequest;
import com.example.chat.entity.User;
import com.example.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> checkUserExist(String username, String email) {
        return userRepository.checkUserExist(username, email);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User checkUser(String username, String password) {
        return userRepository.checkUser(username, password);
    }

    public List<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }



}
