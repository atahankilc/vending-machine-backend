package com.example.vendingmachinebackend.dto;

import com.example.vendingmachinebackend.model.ProductModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {
    private String name;
    private int price;
    private int quantity;
    private String image;

    public ProductDto(ProductModel productModel) {
        this.name = productModel.getName();
        this.price = productModel.getPrice();
        this.quantity = productModel.getQuantity();
        this.image = productModel.getImage();
    }
}
