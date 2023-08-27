package com.example.vendingmachinebackend.controller;

import com.example.vendingmachinebackend.model.Product;
import com.example.vendingmachinebackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    @ResponseBody
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }
}
