package com.example.vendingmachinebackend.controller;

import com.example.vendingmachinebackend.dto.ProductDto;
import com.example.vendingmachinebackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    @RequestMapping("/getAll")
    @ResponseBody
    public List<ProductDto> getProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    @RequestMapping("/add")
    @ResponseBody
    public ProductDto addProduct(@RequestBody ProductDto productDto) {
        return productService.addProduct(productDto);
    }

    @PutMapping
    @RequestMapping("/update")
    @ResponseBody
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productService.updateProduct(productDto);
    }

    @DeleteMapping
    @RequestMapping("/remove/{productName}")
    @ResponseBody
    public ProductDto addProduct(@PathVariable String productName) {
        return productService.removeProduct(productName);
    }
}
