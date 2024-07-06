package com.example.demo.repository;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.User;


@Repository
public interface UserRepository extends JpaRepository< User, String> {
    // Additional custom query methods if needed
    
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
    

    //example only we can use save directly
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.email = :email WHERE u.username = :username")
    void updateUserEmail(String username, String email);
}