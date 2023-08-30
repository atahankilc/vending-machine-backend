package com.example.vendingmachinebackend.repository;

import com.example.vendingmachinebackend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByMailAddress(String mailAddress);
}
