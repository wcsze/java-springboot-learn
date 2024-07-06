package com.example.demo.controller;

import com.example.demo.reqBody.UserReqBody.UpdateEmailReqBody;
import com.example.demo.resBody.UserResBody.UserInfoResBody;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public ResponseEntity<UserInfoResBody> getUserInfo(Authentication authentication) {
        String username = authentication.getName(); // Get username from authentication token
        UserInfoResBody resBody = userService.getUserInfo(username);
        return new ResponseEntity<>(resBody, HttpStatus.OK);
    }

    @PostMapping("/update/email")
    public ResponseEntity<String> updateUserEmail(@RequestBody UpdateEmailReqBody reqBody, Authentication authentication ) {
        String username = authentication.getName(); // Get username from authentication token
        String resBody = userService.updateUserEmail(username, reqBody.getEmail());
        return new ResponseEntity<>(resBody, HttpStatus.OK);
    }
    
}
