package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
   
    boolean existByRefreshToken(String refreshToken);

    Optional<RefreshToken> findByRefreshToken(String refreshToken);

}
