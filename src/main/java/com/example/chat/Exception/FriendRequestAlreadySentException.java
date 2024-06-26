package com.example.chat.Exception;

public class FriendRequestAlreadySentException extends RuntimeException{
    public FriendRequestAlreadySentException(String message) {
        super(message);
    }
}
