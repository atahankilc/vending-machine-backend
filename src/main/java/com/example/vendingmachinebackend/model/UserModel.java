package com.example.vendingmachinebackend.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
@Data
@NoArgsConstructor
public class UserModel {

    @Id
    private String id;

    private String mailAddress;
    private String name;
    private String picture;
    private int wallet;
    private int inserted;

    public UserModel(String mailAddress, String name, String picture) {
        this.mailAddress = mailAddress;
        this.name = name;
        this.picture = picture;
        this.wallet = 0;
        this.inserted = 0;
    }
}
