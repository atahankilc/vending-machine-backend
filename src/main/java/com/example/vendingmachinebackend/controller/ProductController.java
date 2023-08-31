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

    @PostMapping
    @RequestMapping("/update")
    @ResponseBody
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @PostMapping
    @RequestMapping("/add")
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @DeleteMapping
    @RequestMapping("/remove/{productName}")
    public void addProduct(@PathVariable String productName) {
        productService.removeProduct(productName);
    }
}
