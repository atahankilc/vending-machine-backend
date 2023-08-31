package com.example.vendingmachinebackend.service;

import com.example.vendingmachinebackend.dto.CartDto;
import com.example.vendingmachinebackend.dto.CheckoutDto;
import com.example.vendingmachinebackend.model.Product;
import com.example.vendingmachinebackend.model.Status;
import com.example.vendingmachinebackend.model.User;
import com.example.vendingmachinebackend.repository.ProductRepository;
import com.example.vendingmachinebackend.repository.StatusRepository;
import com.example.vendingmachinebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Service
public class CheckoutService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    StatusRepository statusRepository;

    public int collectMoney() {
        Status status = statusRepository.findByField("collectedMoney");
        int collectedMoney = status.getValue();
        status.setValue(0);
        statusRepository.save(status);
        return collectedMoney;
    }

    public CheckoutDto purchase(Map<String, Object> jwtClaim, CartDto cartDto) {
        User user = userRepository.findByMailAddress(jwtClaim.get("email").toString());
        int inserted = user.getInserted();
        int total = 0;
        boolean errorFlag = false;

        for (CartDto.Item item: cartDto.getItemList()) {
            Product product = productRepository.findByName(item.getName());
            if (item.getCount() <= product.getQuantity()) {
                total+= item.getCount() * product.getPrice();
            } else {
                errorFlag = true;
                break;
            }
        }
        if (errorFlag || total > inserted) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ZAAAA");
        }

        Status status = statusRepository.findByField("collectedMoney");
        status.setValue(status.getValue() + total);
        statusRepository.save(status);

        CheckoutDto checkoutDto = new CheckoutDto();
        for (CartDto.Item item: cartDto.getItemList()) {
            Product product = productRepository.findByName(item.getName());
            product.setQuantity(product.getQuantity() - item.getCount());
            productRepository.save(product);
        }

        user.setInserted(inserted - total);
        userRepository.save(user);

        checkoutDto.setReturnedProduct(cartDto);
        checkoutDto.setRemainingCoin(inserted - total);
        return checkoutDto;
    }
}
