package com.example.vendingmachinebackend.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartDto {
    List<ItemDto> itemList;

    @Data
    public static class ItemDto {
        private String name;
        private int count;
    }
}
