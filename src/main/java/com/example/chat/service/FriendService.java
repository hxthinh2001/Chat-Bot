package com.example.chat.service;

import com.example.chat.entity.FriendRequest;
import com.example.chat.entity.User;
import com.example.chat.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FriendService {

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private UserService userService;


    public void sendFriendRequest(Long senderId, Long receiverId) {
        LocalDateTime now = LocalDateTime.now();
        friendRepository.saveFriendRequest(senderId, receiverId, "PENDING", now, now);
    }

    public void acceptFriendRequest(Long senderId, Long receiverId) {
        LocalDateTime now = LocalDateTime.now();
        friendRepository.updateFriendRequest(senderId, receiverId, "ACCEPTED", now);
    }

    public void rejectFriendRequest(Long senderId, Long requestId) {
        LocalDateTime now = LocalDateTime.now();
        friendRepository.updateFriendRequest(senderId, requestId, "REJECTED", now);
    }


    public List<User> listFriendRequest(Long userId) {
        List<FriendRequest> friendsByReceiver = friendRepository.findFriendsByReceiver(userId, "ACCEPTED");
        List<FriendRequest> friendsBySender = friendRepository.findFriendsBySender(userId, "ACCEPTED");

        Set<Long> friendIds = new HashSet<>();

        // Thêm các ID của sender từ các yêu cầu mà user là receiver
        for (FriendRequest request : friendsByReceiver) {
            friendIds.add(request.getSenderId());
        }

        // Thêm các ID của receiver từ các yêu cầu mà user là sender
        for (FriendRequest request : friendsBySender) {
            friendIds.add(request.getReceiverId());
        }
        // Tìm kiếm thông tin của các user có ID trong friendIds
        List<User> friend = new ArrayList<>();
        for (Long id : friendIds){
            User user = userService.findUserById(id);
            friend.add(user);
        }
        return friend;
    }


}
