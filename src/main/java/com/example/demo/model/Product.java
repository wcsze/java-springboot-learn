package com.example.demo.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name="productId", nullable = false)
    private Integer productId;

    @Column(name="stock", nullable = false)
    @Min(0)
    private Integer stock;

    public Product(){
        // Default constructor required by JPA
    }

    public Product(Integer productId, Integer stock){
        this.productId = productId;
        this.stock = stock;
    }

    public Integer getProductId() {
        return productId;
    }

    public Integer getStock() {
        return stock;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
