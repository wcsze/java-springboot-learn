package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.resBody.UserResBody.UserInfoResBody;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;
    
    public UserInfoResBody getUserInfo(String username){
        User user =  userRepo.findByUsername(username).orElseThrow(()->new NotFoundException("user not found"));
        UserInfoResBody resbody = new UserInfoResBody(user.getUsername(),user.getEmail());
        return resbody;
    }

    public String updateUserEmail(String username, String email){
        User user = userRepo.findByUsername(username).orElseThrow(()->new NotFoundException("user not found"));
        user.setEmail(email);
        userRepo.save(user);
        return "User email updated successfully!";
    }


    
}
