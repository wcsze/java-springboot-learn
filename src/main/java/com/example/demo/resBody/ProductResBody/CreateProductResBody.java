package com.example.demo.resBody.ProductResBody;

public class CreateProductResBody {
    private Integer productId;
    private Integer stock; 

    public CreateProductResBody(Integer productId, Integer stock){
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
