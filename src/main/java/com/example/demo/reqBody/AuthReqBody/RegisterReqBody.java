package com.example.demo.reqBody.AuthReqBody;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class RegisterReqBody {
    private String username;
    private String email;
    private String password;
    
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
    

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
