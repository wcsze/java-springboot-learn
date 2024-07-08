package com.example.demo.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.exception.InternalServerException;
import com.example.demo.exception.UnauthorizedException;

import java.util.Optional;
import java.util.UUID;
import java.util.Date;

@Slf4j
@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtsecret;

    private final String ISSUER = "coding_streams_auth_server";

    public boolean validateToken(String jwtToken){
        return parseToken(jwtToken).isPresent();
    }

    private SecretKey getSecretKey(){
        byte[] keyBytes = jwtsecret.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private  Optional<Claims> parseToken(String jwtToken) {
        JwtParser parser = Jwts.parser()
        .verifyWith(getSecretKey())
        .build();

        try{
            return Optional.of(parser.parseSignedClaims(jwtToken)
            .getPayload());
        }
        catch(Exception e){
            log.error(e.getMessage());
            throw new UnauthorizedException("invalid jwt token");
        }
    }

    public Optional<String> getUsernameFromToken(String jwtToken) {
        Optional<Claims> claims = parseToken(jwtToken);
        if(claims.isPresent()){
            return Optional.of(claims.get().getSubject());
        }
        return Optional.empty();
    }

    public String generateAccessToken(String username) {
        try{
            Date currentDt = new Date();
            Date expirationDT = DateUtils.addMinutes(currentDt, 15);

            return Jwts.builder()
            .id(UUID.randomUUID().toString())
            .issuer(ISSUER)
            .subject(username)
            .issuedAt(currentDt)
            .expiration(expirationDT)
            .signWith(getSecretKey())
            .compact();
        }
        catch(Exception e){
            log.error(e.getMessage());
            throw new InternalServerException("generate access token failed");
        }
    }

    public String generateRefreshToken() {
        try{
            Date currentDt = new Date();
            Date expirationDT = DateUtils.addDays(currentDt, 1);

            return Jwts.builder()
            .id(UUID.randomUUID().toString())
            .issuer(ISSUER)
            .issuedAt(currentDt)
            .expiration(expirationDT)
            .signWith(getSecretKey())
            .compact();
        }
        catch(Exception e){
            log.error(e.getMessage());
            throw new InternalServerException("generate access token failed");
        }
    }
}

