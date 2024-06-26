package com.example.chat.controller;

import com.example.chat.Exception.UserNotFoundException;
import com.example.chat.entity.User;
import com.example.chat.service.FriendRequestService;
import com.example.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat/friends")
public class FriendController {

    @Autowired
    private FriendRequestService friendRequestService;
    @Autowired
    private UserService userService;

    @PostMapping("/send")
    public String sendFriendRequest(@RequestParam Long senderId,@RequestParam Long receiverId) {
        User exitSender = userService.findUserById(senderId);
        User exitReceiver = userService.findUserById(receiverId);

        if (exitSender == null || exitReceiver == null) {
            throw new UserNotFoundException("User not found");
        }

        friendRequestService.sendFriendRequest(senderId, receiverId);
        return "Friend request sent";
    }
}
