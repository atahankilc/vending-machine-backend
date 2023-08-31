package com.example.vendingmachinebackend.service;

import com.example.vendingmachinebackend.dto.ProductDto;
import com.example.vendingmachinebackend.model.ProductModel;
import com.example.vendingmachinebackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    private ProductModel saveProduct(ProductModel productModel) {
        return productRepository.save(productModel);
    }

    public List<ProductDto> getAllProducts() {
        List<ProductDto> productDtoList = new ArrayList<>();
        productRepository.findAll().forEach(productModel -> productDtoList.add(new ProductDto(productModel)));
        return productDtoList;
    }

    public ProductDto updateProduct(ProductDto productDto) {
        ProductModel productModel = productRepository.findByName(productDto.getName());
        productModel.setPrice(productDto.getPrice());
        productModel.setQuantity(productDto.getQuantity());
        saveProduct(productModel);
        return productDto;
    }

    public ProductDto addProduct(ProductDto productDto) {
        if(productRepository.findByName(productDto.getName()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There Is A Product With Same Name!");
        }
        ProductModel productModel = new ProductModel();
        productModel.setName(productDto.getName());
        productModel.setPrice(productDto.getPrice());
        productModel.setQuantity(productDto.getQuantity());
        productModel.setImage(productDto.getImage());
        saveProduct(productModel);
        return productDto;
    }

    public ProductDto removeProduct(String productName) {
        if(productRepository.findByName(productName) == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Product Found With Given Name!");
        }
        ProductModel productModel = productRepository.deleteByName(productName);
        return new ProductDto(productModel);
    }

}
