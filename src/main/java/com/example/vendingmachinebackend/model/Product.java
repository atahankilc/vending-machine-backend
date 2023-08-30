package com.example.vendingmachinebackend.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("products")
@Data
@NoArgsConstructor
public class Product {

    @Id
    private String id;

    private String name;
    private int price;
    private int quantity;
    private String image;

    public Product(String id, String name, int price, int quantity, String image) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
    }
}
