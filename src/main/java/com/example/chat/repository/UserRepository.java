package com.example.chat.repository;


import com.example.chat.entity.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepository extends CrudRepository<User, Long> {

    @Query(""" 
            SELECT * FROM users WHERE id = :id;
            """)
    User findUserById(Long id);

    @Query("""
            SELECT * FROM users WHERE username = :username  OR email = :email;
            """)
    List<User> checkUserExist(@Param("username") String username, @Param("email") String email);


    @Query("""
            SELECT * FROM users WHERE username = :username  AND password = :password;
            """)
    User checkUser(@Param("username") String username, @Param("password") String password);

    @Query("SELECT * FROM users WHERE username LIKE CONCAT('%', :username, '%')")
    List<User> findByUsername(@Param("username") String username);


}