package com.example.demo.model;
import jakarta.persistence.*;

@Entity
@Table(name = "users", indexes = {@Index(name = "idx_username", columnList = "username")})
public class User {

    @Id
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;

    public User() {
        // Default constructor required by JPA
    }

    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername(){
        return username;
    }

    public String getEmail(){
        return email;
    }
    
    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
