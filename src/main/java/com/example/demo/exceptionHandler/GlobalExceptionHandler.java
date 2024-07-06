package com.example.demo.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.UnauthorizedException;
import com.example.demo.exception.InternalServerException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFoundHandler(NotFoundException ex) {
        log.error("Not Found Error: " + ex.getMessage());
        return new ResponseEntity<>("Not Found Error: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> badRequestHandler(BadRequestException ex) {
        log.error("Bad Request Error: " + ex.getMessage());
        return new ResponseEntity<>("Bad Request Error: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<String> internalServerErrorHandler(InternalServerException ex) {
        log.error("Internal Server Error: " + ex.getMessage());
        return new ResponseEntity<>("Internal Server Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> unauthorizedHandler(UnauthorizedException ex) {
        log.error("Unauthorized Error: " + ex.getMessage());
        return new ResponseEntity<>("Unauthorized Error: " + ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
