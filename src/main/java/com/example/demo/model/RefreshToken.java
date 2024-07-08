package com.example.demo.model;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "refreshTokens")
public class RefreshToken {
    
    @Id
    @Column(name="tokenId")
    private int tokenId;

    @ManyToOne
    @JoinColumn(name="username", referencedColumnName = "username", nullable = false)
    private User user;

    @Column(name = "refreshToken", nullable = false, unique = true)
    private String refreshToken;

    @Column(name = "createdAt", nullable = false)
    private Date createdAt;

    public RefreshToken(){
        // default constructor
    }

    public RefreshToken(User user, String refreshToken){
        this.createdAt = new Date();
        this.user = user;
        this.refreshToken = refreshToken;
    }

    public User getUser() {
        return user;
    }
}
