package com.example.chat.controller;

import com.example.chat.Exception.UserNotFoundException;
import com.example.chat.entity.User;
import com.example.chat.service.FriendService;
import com.example.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat/friends")
public class FriendController {

    @Autowired
    private final FriendService friendRequestService;

    @Autowired
    private final UserService userService;

    public FriendController(FriendService friendRequestService, UserService userService) {
        this.friendRequestService = friendRequestService;
        this.userService = userService;
    }

    @PostMapping("/send")
    public String sendFriendRequest(@RequestParam Long senderId, @RequestParam Long receiverId) {
        User exitSender = userService.findUserById(senderId);
        User exitReceiver = userService.findUserById(receiverId);

        if (senderId.equals(receiverId)) {
            throw new UserNotFoundException("You can't send friend request to yourself");
        }
        if (exitSender == null || exitReceiver == null) {
            throw new UserNotFoundException("User not found");
        }

        friendRequestService.sendFriendRequest(senderId, receiverId);
        return "Friend request sent";
    }

    @PostMapping("/accept")
    public String acceptFriendRequest(@RequestParam Long senderId, @RequestParam Long receiverId) {
        User exitReceiver = userService.findUserById(receiverId);

        if (exitReceiver == null) {
            throw new UserNotFoundException("User not found");
        }

        friendRequestService.acceptFriendRequest(senderId, receiverId);
        return "Friend request accepted";
    }

    @GetMapping("/list")
    public List<User> listFriendRequest(@RequestParam Long userId) {
        User exitUser = userService.findUserById(userId);

        if (exitUser == null) {
            throw new UserNotFoundException("User not found");
        }

        return  friendRequestService.listFriendRequest(userId);
    }

}
