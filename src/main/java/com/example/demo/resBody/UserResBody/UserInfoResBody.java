package com.example.demo.resBody.UserResBody;

public class UserInfoResBody {
    private String username;
    private String email;

    public UserInfoResBody(String username, String email){
        this.username = username;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
    public String getUsername() {
        return username;
    }
}
