package com.example.vendingmachinebackend.dto;

import lombok.Data;

@Data
public class CheckoutDto {

    private CartDto returnedProduct;
    private int returnedCoin;
}
