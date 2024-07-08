package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.UnauthorizedException;
import com.example.demo.model.RefreshToken;
import com.example.demo.model.User;
import com.example.demo.repository.RefreshTokenRepository;
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
    private RefreshTokenRepository refreshTokenRepository;

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
        String refreshToken = jwtService.generateRefreshToken();

        //save refresh token in db
        RefreshToken token = new RefreshToken(user, refreshToken);
        refreshTokenRepository.save(token);
    
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
        String refreshToken = jwtService.generateRefreshToken();

        //save refresh token in db
        RefreshToken token = new RefreshToken(user, refreshToken);
        refreshTokenRepository.save(token);

        LoginResBody resBody = new LoginResBody(accessToken, refreshToken);
        return resBody;

    }

    public RefreshTokenResBody refreshToken(String refreshToken){
        RefreshToken token = refreshTokenRepository.findByRefreshToken(refreshToken).orElseThrow(()-> new NotFoundException("refresh token not found"));
        
        String accessToken = jwtService.generateAccessToken(token.getUser().getUsername());

        RefreshTokenResBody resBody = new RefreshTokenResBody(accessToken);
        return resBody;
    } 
    

}
