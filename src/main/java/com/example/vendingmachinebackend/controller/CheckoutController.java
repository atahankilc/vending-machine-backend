package com.example.vendingmachinebackend.controller;

import com.example.vendingmachinebackend.dto.CartDto;
import com.example.vendingmachinebackend.dto.CheckoutDto;
import com.example.vendingmachinebackend.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkout")
@CrossOrigin
public class CheckoutController {

    @Autowired
    CheckoutService checkoutService;

    @PostMapping
    @ResponseBody
    public CheckoutDto purchase(@AuthenticationPrincipal Jwt jwt, @RequestBody CartDto cartDto){
        return checkoutService.purchase(jwt.getClaims(), cartDto);
    }


}
