package com.example.vendingmachinebackend.repository;

import com.example.vendingmachinebackend.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserModel, String> {

    UserModel findByMailAddress(String mailAddress);
}
