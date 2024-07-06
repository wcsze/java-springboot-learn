package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.reqBody.AuthReqBody.LoginReqBody;
import com.example.demo.reqBody.AuthReqBody.RefreshTokenReqBody;
import com.example.demo.reqBody.AuthReqBody.RegisterReqBody;
import com.example.demo.resBody.AuthResBody.LoginResBody;
import com.example.demo.resBody.AuthResBody.RefreshTokenResBody;
import com.example.demo.resBody.AuthResBody.RegisterResBody;
import com.example.demo.service.AuthService;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResBody> register(@RequestBody RegisterReqBody reqBody){
        RegisterResBody resBody = authService.registerUser(reqBody.getUsername(), reqBody.getEmail(), reqBody.getPassword());
        return new ResponseEntity<>(resBody, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResBody> login(@RequestBody LoginReqBody reqBody){
        LoginResBody resBody = authService.login(reqBody.getUsername(), reqBody.getPassword());
        return new ResponseEntity<>(resBody, HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResBody> RefreshToken(@RequestBody RefreshTokenReqBody reqBody) {
        RefreshTokenResBody resBody = authService.refreshToken(reqBody.getRefreshToken());
        return new ResponseEntity<>(resBody, HttpStatus.OK);
    }
    
}
