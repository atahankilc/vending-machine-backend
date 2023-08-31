package com.example.vendingmachinebackend.service;

import com.example.vendingmachinebackend.model.Product;
import com.example.vendingmachinebackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public void addProduct(Product product) {
        if(productRepository.findByName(product.getName()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ZAAAA");
        }
        productRepository.save(product);
    }

}
