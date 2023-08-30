package com.example.vendingmachinebackend.repository;

import com.example.vendingmachinebackend.model.Status;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StatusRepository extends MongoRepository<Status, String> {

    Status findByField(String field);
}
