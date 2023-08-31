package com.example.vendingmachinebackend.service;

import com.example.vendingmachinebackend.dto.UserDto;
import com.example.vendingmachinebackend.model.Status;
import com.example.vendingmachinebackend.model.User;
import com.example.vendingmachinebackend.repository.StatusRepository;
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
    @Autowired
    StatusRepository statusRepository;

    public UserDto getUserInfo(Map<String, Object> jwtClaim) {
        User user = userRepository.findByMailAddress(jwtClaim.get("email").toString());
        if (user == null) {
            user = userRepository.save(new User(jwtClaim.get("email").toString(), jwtClaim.get("name").toString(), jwtClaim.get("picture").toString()));
        }
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setPicture(user.getPicture());
        userDto.setWallet(user.getWallet());
        userDto.setInserted(user.getInserted());
        return userDto;
    }

    public int insertCoin(String mailAddress, int insertedCoin) {
        User user = userRepository.findByMailAddress(mailAddress);
        if (insertedCoin > user.getWallet()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ZAAAA");
        }
        user.setWallet(user.getWallet() - insertedCoin);
        user.setInserted(user.getInserted() + insertedCoin);
        userRepository.save(user);
        return insertedCoin;
    }

    public void refundCoin(String mailAddress) {
        User user = userRepository.findByMailAddress(mailAddress);
        user.setWallet(user.getWallet() + user.getInserted());
        user.setInserted(0);
        userRepository.save(user);
    }

    public void enterToSupplierMode(int code) {
        Status status = statusRepository.findByField("supplierCode");
        if (code != status.getValue()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ZAAAA");
        }
    }
}
