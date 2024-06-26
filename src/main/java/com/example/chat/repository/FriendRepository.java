package com.example.chat.repository;

import com.example.chat.entity.FriendRequest;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface FriendRepository extends CrudRepository<FriendRequest, Long> {

    @Modifying
    @Query("""
            INSERT INTO friend_requests (sender_id, receiver_id, status, created_at, update_at)
            VALUES (:senderId, :receiverId, :status, :createdAt, :updatedAt)
            """)
    void saveFriendRequest(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId, @Param("status") String status, @Param("createdAt") LocalDateTime createdAt, @Param("updatedAt") LocalDateTime updatedAt);

    @Modifying
    @Query("""
            UPDATE friend_requests SET status = :status, update_at = :updatedAt
            WHERE sender_id = :senderId AND receiver_id = :receiverId
            """)
    void updateFriendRequest(@Param("id") Long id, @Param("status") String status, @Param("updateAt") LocalDateTime updateAt);
}
