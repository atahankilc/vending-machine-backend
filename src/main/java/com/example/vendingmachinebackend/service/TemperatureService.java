package com.example.vendingmachinebackend.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TemperatureService {

    private float temperature = 30f;
    private boolean coolingFlag = false;

    @Scheduled(fixedRate = 10 * 60000) // every 10 minutes
    public void cooler() {
        if (temperature > 20f) {
            coolingFlag = true;
        } else if (temperature < 10f) {
            coolingFlag = false;
        }
    }

    @Scheduled(fixedRate = 60000) // every 1 minute
    public void coolingMachine() {
        if(coolingFlag) {
            temperature -= 0.1;
            System.out.println("Cooling Machine, Temperature: " + temperature);
        }
    }

    @Scheduled(fixedRate = 5 * 60000) // every 5 minute
    public void warmingMachine() {
        temperature += 0.1;
        System.out.println("Warming Machine, Temperature: " + temperature);
    }
}
