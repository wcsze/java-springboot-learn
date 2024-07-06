package com.example.demo.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
@Table(name="purchaseHistories")
public class PurchaseHistory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer historyId;

    @ManyToOne
    @JoinColumn(name="username", referencedColumnName = "username")
    private User user;

    @ManyToOne
    @JoinColumn(name="productId", referencedColumnName = "productId")
    private Product product;

    @Column(name="quantity", nullable = false)
    @Min(0)
    private Integer quantity;

    public PurchaseHistory(){

    }

    public PurchaseHistory(User user, Product product, int quantity) {
        this.user = user;
        this.product = product;
        this.quantity = quantity;
    }

    public Integer getHistoryId() {
        return historyId;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public User getUser() {
        return user;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setUser(User user) {
        this.user = user;
    }



    
}
