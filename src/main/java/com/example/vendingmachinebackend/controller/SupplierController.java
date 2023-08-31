package com.example.vendingmachinebackend.controller;

import com.example.vendingmachinebackend.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/supplier")
@CrossOrigin
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @PutMapping
    @RequestMapping("/authenticate")
    public void authenticateSupplier(@RequestBody int code) {
        supplierService.authenticate(code);
    }

    @PutMapping
    @RequestMapping("/collect")
    @ResponseBody
    public int collectMoney() {
        return supplierService.collectMoney();
    }
}
