package com.example.demo.controller;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import com.example.demo.reqBody.ProductReqBody.CreateProductReqBody;
import com.example.demo.reqBody.ProductReqBody.PurchaseReqBody;
import com.example.demo.resBody.ProductResBody.CreateProductResBody;
import com.example.demo.resBody.ProductResBody.GetProductListResBody;
import com.example.demo.resBody.ProductResBody.GetProductResBody;
import com.example.demo.service.ProductService;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
@RequestMapping("/api/product")
public class ProductController {
  
    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<CreateProductResBody> createProduct(@RequestBody CreateProductReqBody reqBody) {
        CreateProductResBody resBody = productService.createProduct(reqBody.getProductId(), reqBody.getStock());
        return new ResponseEntity<>(resBody, HttpStatus.OK);
    }

    @GetMapping("/info/{productId}")
    public ResponseEntity<GetProductResBody> getProduct(@PathVariable Integer productId) {
        GetProductResBody resBody = productService.getProduct(productId);
        return new ResponseEntity<>(resBody, HttpStatus.OK);
    }

    @GetMapping("/list/available")
    public ResponseEntity<GetProductListResBody> getAvailableProductList() {
        GetProductListResBody resBody = productService.getAllAvailableProduct();
        return new ResponseEntity<>(resBody, HttpStatus.OK);
    }

    @PostMapping("/purchase")
    public ResponseEntity<String> purchaseProduct(@RequestBody PurchaseReqBody reqBody, Authentication authentication) {
        String resBody = productService.purchaseProduct(authentication.getName(), reqBody.getProductId(), reqBody.getQuantity());
        return new ResponseEntity<>(resBody, HttpStatus.OK);
    }
    

}
