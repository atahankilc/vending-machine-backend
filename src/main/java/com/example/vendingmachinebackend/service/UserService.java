package com.example.vendingmachinebackend.service;

import com.example.vendingmachinebackend.dto.UserDto;
import com.example.vendingmachinebackend.model.UserModel;
import com.example.vendingmachinebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    private UserModel saveUser(UserModel userModel) {
        return userRepository.save(userModel);
    }

    public UserDto getUserInfo(Map<String, Object> jwtClaim) {
        UserModel userModel = userRepository.findByMailAddress(jwtClaim.get("email").toString());
        if (userModel == null) {
            String mailAddress = jwtClaim.get("email").toString();
            String name = jwtClaim.get("name").toString();
            String picture = jwtClaim.get("picture").toString();
            userModel = saveUser(new UserModel(mailAddress, name, picture));
        }
        return new UserDto(userModel);
    }

    public UserDto insertCoin(String mailAddress, int insertedCoin) {
        UserModel userModel = userRepository.findByMailAddress(mailAddress);
        if (insertedCoin > userModel.getWallet()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Inserted More Than What You Have!");
        }
        userModel.setWallet(userModel.getWallet() - insertedCoin);
        userModel.setInserted(userModel.getInserted() + insertedCoin);
        saveUser(userModel);
        return new UserDto(userModel);
    }

    public UserDto refundCoin(String mailAddress) {
        UserModel userModel = userRepository.findByMailAddress(mailAddress);
        userModel.setWallet(userModel.getWallet() + userModel.getInserted());
        userModel.setInserted(0);
        userRepository.save(userModel);
        return new UserDto(userModel);
    }
}
