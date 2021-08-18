package com.app.bikeinventory.service;

import com.app.bikeinventory.model.Bike;

import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    public int addBike(String brand, String color) {
        // TODO: get next id instead of using 0
        int id = 0;
        Bike bike = Bike.builder()
                .id(id)
                .brand(brand)
                .color(color)
                .build();

        // TODO: add bike to inventory and return id of this new bike

        System.out.println("Added new bike to inventory: " + bike);
        return bike.getId();
    }

    // TODO: add other methods to handle controller requests
}
