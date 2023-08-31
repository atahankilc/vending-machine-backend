package com.example.vendingmachinebackend.repository;

import com.example.vendingmachinebackend.model.StatusModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StatusRepository extends MongoRepository<StatusModel, String> {

    StatusModel findByField(String field);
}
