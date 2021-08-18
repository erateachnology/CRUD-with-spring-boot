package com.app.bikeinventory.controller;

import com.app.bikeinventory.service.InventoryService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }


    // This exposes a POST endpoint to add a new bike
    // You can call this endpoint e.g. from your console with this command:
    //      curl -XPOST "http://localhost:8080/bike/new?brand=XYZ+Bikes&color=Black"
    @PostMapping("/bike/new")
    public Integer addBike(@RequestParam String brand, @RequestParam String color) {

        System.out.println("About to add a new bike: brand=" + brand + ", color=" + color);
        return service.addBike(brand, color);
    }

    // TODO add other REST endpoints
}
