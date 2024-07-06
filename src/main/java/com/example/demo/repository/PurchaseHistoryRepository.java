package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.PurchaseHistory;
import java.util.List;

public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Integer> {
    
    @Query("SELECT p FROM PurchaseHistory p WHERE p.user.username = :username")
    List<PurchaseHistory> findByusername(String username);

    @Query("SELECT p FROM PurchaseHistory p WHERE p.product.productId =:productId")
    List<PurchaseHistory> findByProductId(Integer productId);

    @Query("SELECT p FROM PurchaseHistory p WHERE p.user.username = :username AND p.product.productId = :productId")
    List<PurchaseHistory> findByusernameAndProductId(String username, Integer productId);
}
