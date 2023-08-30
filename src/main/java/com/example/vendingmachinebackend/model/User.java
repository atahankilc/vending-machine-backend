package com.example.vendingmachinebackend.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
@Data
@NoArgsConstructor
public class User {

    @Id
    private String id;

    private String mailAddress;
    private String name;
    private String picture;
    private int wallet;
    private int inserted;

    public User(String mailAddress, String name, String picture) {
        super();
        this.mailAddress = mailAddress;
        this.name = name;
        this.picture = picture;
        this.wallet = 0;
        this.inserted = 0;
    }

    public User(String id, String mailAddress, String name, String picture, int wallet, int inserted) {
        super();
        this.id = id;
        this.mailAddress = mailAddress;
        this.name = name;
        this.picture = picture;
        this.wallet = wallet;
        this.inserted = inserted;
    }
}
