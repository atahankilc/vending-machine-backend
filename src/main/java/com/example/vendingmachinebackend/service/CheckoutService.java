package com.example.vendingmachinebackend.service;

import com.example.vendingmachinebackend.dto.CartDto;
import com.example.vendingmachinebackend.dto.CheckoutDto;
import com.example.vendingmachinebackend.model.ProductModel;
import com.example.vendingmachinebackend.model.StatusModel;
import com.example.vendingmachinebackend.model.UserModel;
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

    public CheckoutDto purchase(Map<String, Object> jwtClaim, CartDto cartDto) {
        UserModel userModel = userRepository.findByMailAddress(jwtClaim.get("email").toString());
        int inserted = userModel.getInserted();
        int total = 0;
        boolean notEnoughQuantityFlag = false;

        for (CartDto.Item item: cartDto.getItemList()) {
            ProductModel product = productRepository.findByName(item.getName());
            if (item.getCount() <= product.getQuantity()) {
                total+= item.getCount() * product.getPrice();
            } else {
                notEnoughQuantityFlag = true;
                break;
            }
        }
        if (notEnoughQuantityFlag) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product Quantity Does Not Match With Your Order!");
        } else if (total > inserted) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You Have To Pay For What You Want!");
        }

        StatusModel status = statusRepository.findByField("collectedMoney");
        status.setValue(status.getValue() + total);
        statusRepository.save(status);

        CheckoutDto checkoutDto = new CheckoutDto();
        for (CartDto.Item item: cartDto.getItemList()) {
            ProductModel product = productRepository.findByName(item.getName());
            product.setQuantity(product.getQuantity() - item.getCount());
            productRepository.save(product);
        }

        int remaining = inserted - total;
        userModel.setInserted(0);
        userModel.setWallet(userModel.getWallet() + remaining);
        userRepository.save(userModel);

        checkoutDto.setReturnedProduct(cartDto);
        checkoutDto.setReturnedCoin(remaining);
        return checkoutDto;
    }
}
