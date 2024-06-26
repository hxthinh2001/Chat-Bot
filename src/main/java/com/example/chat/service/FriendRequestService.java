package com.example.chat.service;

import com.example.chat.entity.FriendRequest;
import com.example.chat.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FriendRequestService {

    @Autowired
    private FriendRepository friendRepository;

    public void sendFriendRequest(Long senderId,Long receiverId) {
        LocalDateTime now = LocalDateTime.now();
        friendRepository.saveFriendRequest(senderId, receiverId, "PENDING", now, now);
    }

    public void acceptFriendRequest(Long requestId) {
        LocalDateTime now = LocalDateTime.now();
        friendRepository.updateFriendRequest(requestId, "ACCEPTED", now);
    }

    public void rejectFriendRequest(Long requestId) {
        LocalDateTime now = LocalDateTime.now();
        friendRepository.updateFriendRequest(requestId, "REJECTED", now);
    }

}
