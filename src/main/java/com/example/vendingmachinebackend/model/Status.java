package com.example.vendingmachinebackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("status")
@Data
public class Status {

    @Id
    private String ip;

    private String field;
    private int value;
}
