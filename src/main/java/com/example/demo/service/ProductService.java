package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.resBody.ProductResBody.CreateProductResBody;
import com.example.demo.resBody.ProductResBody.GetProductListResBody;
import com.example.demo.resBody.ProductResBody.GetProductResBody;
import java.util.List;
@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PurchaseHistoryService purchaseHistoryService;

    @Autowired UserRepository userRepository;

    public CreateProductResBody createProduct(Integer productId, Integer stock){
        Product product = new Product(productId, stock);
        productRepository.save(product);
        return new CreateProductResBody(productId, stock);
    }

    @Cacheable(key = "#productId", value = "products" )
    public GetProductResBody getProduct(Integer productId){
        Product product = productRepository.findById(productId).orElseThrow(()-> new NotFoundException("product not found"));
        return new GetProductResBody(product.getProductId(), product.getStock());
    }

    @Cacheable(value = "availableProducts")
    public GetProductListResBody getAllAvailableProduct(){
        List<Product> products = productRepository.findAvailableProducts();
        return new GetProductListResBody(products);
    }

    public String purchaseProduct(String username, Integer productId, Integer quantity){
        User user = userRepository.findByUsername(username).orElseThrow(()-> new NotFoundException("user not found"));
        Product product = productRepository.findById(productId).orElseThrow(()-> new NotFoundException("product not found"));
        if(product.getStock() < quantity){
            throw new BadRequestException("product out of stock");
        }
        product.setStock(product.getStock()-quantity);
        productRepository.save(product);
        purchaseHistoryService.createPurchaseHistory(user, product, quantity);
        return "purchase is processing";
    }



}
