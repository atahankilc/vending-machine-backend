package com.example.vendingmachinebackend.dto;

import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String picture;
    private int wallet;
    private int inserted;
}
