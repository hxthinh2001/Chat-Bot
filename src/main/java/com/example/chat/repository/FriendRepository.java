package com.example.chat.repository;

import com.example.chat.entity.FriendRequest;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface FriendRepository extends CrudRepository<FriendRequest, Long> {

    @Query("""

             SELECT * FROM `friend_requests`
                 WHERE sender_id = :senderId AND receiver_id = :receiverId
            """)
    FriendRequest findFriendRequest(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId);

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
    void updateFriendRequest(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId, @Param("status") String status, @Param("updatedAt") LocalDateTime updatedAt);

    @Query("""
            SELECT * FROM `friend_requests`
            WHERE receiver_id = :receiverId AND status= :status
            """)
    List<FriendRequest> findFriendsByReceiver(@Param("receiverId") Long receiverId, @Param("status") String status);

    @Query("""
            SELECT * FROM `friend_requests`
            WHERE sender_id = :senderId AND status= :status
            """)
    List<FriendRequest> findFriendsBySender(@Param("senderId") Long senderId, @Param("status") String status);

    @Query("""
                DELETE FROM  `friend_requests`
                WHERE sender_id = :senderId AND receiver_id = :receiverId AND status = :status
            """)
    FriendRequest rejectFriendRequest(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId, @Param("status") String status);

}
