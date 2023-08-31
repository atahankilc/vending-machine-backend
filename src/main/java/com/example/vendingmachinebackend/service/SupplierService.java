package com.example.vendingmachinebackend.service;

import com.example.vendingmachinebackend.model.StatusModel;
import com.example.vendingmachinebackend.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SupplierService {

    @Autowired
    StatusRepository statusRepository;

    public void authenticate(int code) {
        StatusModel status = statusRepository.findByField("supplierCode");
        if (code != status.getValue()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong Code!");
        }
    }

    public int collectMoney() {
        StatusModel status = statusRepository.findByField("collectedMoney");
        int collectedMoney = status.getValue();
        status.setValue(0);
        statusRepository.save(status);
        return collectedMoney;
    }
}
