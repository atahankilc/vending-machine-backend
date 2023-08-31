package com.example.vendingmachinebackend.dto;

import com.example.vendingmachinebackend.model.UserModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private String name;
    private String picture;
    private int wallet;
    private int inserted;

    public UserDto(UserModel userModel) {
        this.name = userModel.getName();
        this.picture = userModel.getPicture();
        this.wallet = userModel.getWallet();
        this.inserted = userModel.getInserted();
    }
}
