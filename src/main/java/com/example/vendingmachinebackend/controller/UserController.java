package com.example.vendingmachinebackend.controller;

import com.example.vendingmachinebackend.dto.UserDto;
import com.example.vendingmachinebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    @RequestMapping("/me")
    @ResponseBody
    public UserDto getUserInfo(@AuthenticationPrincipal Jwt jwt){
        return userService.getUserInfo(jwt.getClaims());
    }

    @PutMapping
    @RequestMapping("/addMoney")
    @ResponseBody
    public UserDto addMoney(@AuthenticationPrincipal Jwt jwt, @RequestBody int addedMoney){
        return userService.addMoney(jwt.getClaims().get("email").toString(), addedMoney);
    }

    @PutMapping
    @RequestMapping("/insertCoin")
    @ResponseBody
    public UserDto insertCoin(@AuthenticationPrincipal Jwt jwt, @RequestBody int insertedCoin){
        return userService.insertCoin(jwt.getClaims().get("email").toString(), insertedCoin);
    }

    @PutMapping
    @RequestMapping("/refundCoin")
    @ResponseBody
    public UserDto refundCoin(@AuthenticationPrincipal Jwt jwt){
        return userService.refundCoin(jwt.getClaims().get("email").toString());
    }
}
