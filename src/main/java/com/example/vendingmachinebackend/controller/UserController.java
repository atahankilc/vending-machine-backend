package com.example.vendingmachinebackend.controller;

import com.example.vendingmachinebackend.dto.UserDto;
import com.example.vendingmachinebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
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

    @PostMapping
    @RequestMapping("/insertCoin")
    @ResponseBody
    public int insertCoin(@AuthenticationPrincipal Jwt jwt, @RequestBody int insertedCoin){
        return userService.insertCoin(jwt.getClaims().get("email").toString(), insertedCoin);
    }

    @GetMapping
    @RequestMapping("/refundCoin")
    public void refundCoin(@AuthenticationPrincipal Jwt jwt){
        userService.refundCoin(jwt.getClaims().get("email").toString());
    }


}
