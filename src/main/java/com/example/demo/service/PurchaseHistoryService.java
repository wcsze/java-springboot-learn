package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.model.PurchaseHistory;
import com.example.demo.model.User;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.PurchaseHistoryRepository;
import com.example.demo.resBody.PurchaseHistoryResBody.GetPurchaseHistoryListResBody;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PurchaseHistoryService {
    
    @Autowired
    private PurchaseHistoryRepository purchaseHistoryRepository;

    @Autowired ProductRepository productRepository;

    @Async
    public void createPurchaseHistory(User user, Product product, Integer quantity){
        try{
            Thread.sleep(60000);
        }
        catch(InterruptedException e){
            log.error("thread interrupted exception:"+ e.getMessage());
            product.setStock(product.getStock()+quantity); //roll back quantity if any fail
            productRepository.save(product);
            log.info("pruchasing order failed");
            Thread.currentThread().interrupt();
        }
        PurchaseHistory purchaseHistory = new PurchaseHistory(user, product, quantity);
        purchaseHistoryRepository.save(purchaseHistory);
        log.info("purchase history " + purchaseHistory.getHistoryId() + " is created successfully on " + Thread.currentThread().getName());
    }

    public GetPurchaseHistoryListResBody getAllPurchaseHistoryList(){
        List<PurchaseHistory> purchaseHistories = purchaseHistoryRepository.findAll();
        return new GetPurchaseHistoryListResBody(purchaseHistories);
    }

    public GetPurchaseHistoryListResBody getUserPurchaseHistoryList(String username){
        List<PurchaseHistory> purchaseHistories = purchaseHistoryRepository.findByusername(username);
        return new GetPurchaseHistoryListResBody(purchaseHistories);
    }
}
