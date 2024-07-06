package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.UnauthorizedException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.resBody.AuthResBody.LoginResBody;
import com.example.demo.resBody.AuthResBody.RefreshTokenResBody;
import com.example.demo.resBody.AuthResBody.RegisterResBody;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public RegisterResBody registerUser(String username, String email, String password){
        if(userRepository.existsByEmail(email)){
            throw new BadRequestException("email exists");
        }
        if(userRepository.existsByUsername(username)){
            throw new BadRequestException("username exists");
        }

         // Hash the password
        String hashedPassword = passwordEncoder.encode(password);

        User user = new User(username, email, hashedPassword);
        userRepository.save(user);

        //generate access token & access token

        String accessToken = jwtService.generateAccessToken(user.getUsername());
        String refreshToken = jwtService.generateRefreshToken(user.getUsername());
    
        RegisterResBody resBody = new RegisterResBody(accessToken, refreshToken);
        return resBody;
    }

    public LoginResBody login(String username, String password){
        User user = userRepository.findByUsername(username).orElseThrow(()-> new NotFoundException("username not found"));

        //compare password
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new UnauthorizedException("incorrect password");
        }

        String accessToken = jwtService.generateAccessToken(user.getUsername());
        String refreshToken = jwtService.generateRefreshToken(user.getUsername());

        LoginResBody resBody = new LoginResBody(accessToken, refreshToken);
        return resBody;

    }

    public RefreshTokenResBody refreshToken(String refreshToken){
        String username = jwtService.getUsernameFromToken(refreshToken).orElseThrow(()->new BadRequestException("invalid refresh token"));

        String accessToken = jwtService.generateAccessToken(username);
        String newRefreshToken = jwtService.generateRefreshToken(username);

        RefreshTokenResBody resBody = new RefreshTokenResBody(accessToken, newRefreshToken);
        return resBody;



    } 

}
