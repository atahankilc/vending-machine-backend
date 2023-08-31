package com.example.vendingmachinebackend.repository;

import com.example.vendingmachinebackend.model.ProductModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductModel, String> {

    ProductModel findByName(String name);

    ProductModel deleteByName(String name);
}
