package com.example.vendingmachinebackend.dto;

import lombok.Data;

@Data
public class CheckoutDto {

    private int remainingCoin;
    private CartDto returnedProduct;
}
