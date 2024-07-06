package com.example.demo.resBody.ProductResBody;

import java.util.List;

import com.example.demo.model.Product;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GetProductListResBody {
    private List<Product> productList;

    public GetProductListResBody(List<Product> productList){
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
