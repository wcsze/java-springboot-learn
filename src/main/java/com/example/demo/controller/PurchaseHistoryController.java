package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.resBody.PurchaseHistoryResBody.GetPurchaseHistoryListResBody;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import com.example.demo.service.PurchaseHistoryService;

@Controller
@RequestMapping("/api/purchaseHistory")
public class PurchaseHistoryController {

    @Autowired
    private PurchaseHistoryService purchaseHistoryService;

    @GetMapping("/list")
    public ResponseEntity<GetPurchaseHistoryListResBody> getUserPurchaseHistory(Authentication authentication) {
        String username = authentication.getName();
        GetPurchaseHistoryListResBody resBody = purchaseHistoryService.getUserPurchaseHistoryList(username);
        return new ResponseEntity<>(resBody, HttpStatus.OK);
    }
    
}
