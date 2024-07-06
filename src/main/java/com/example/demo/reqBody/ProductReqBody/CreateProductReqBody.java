package com.example.demo.reqBody.ProductReqBody;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class CreateProductReqBody {
    private Integer productId;
    private Integer stock; 
    
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
