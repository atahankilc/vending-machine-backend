package com.example.vendingmachinebackend.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartDto {
    List<Item> itemList;

    @Data
    public static class Item {
        private String name;
        private int count;
    }
}
