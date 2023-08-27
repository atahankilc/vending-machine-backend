package com.example.vendingmachinebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.example.vendingmachinebackend.repository")
public class VendingMachineBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(VendingMachineBackendApplication.class, args);
    }

}
