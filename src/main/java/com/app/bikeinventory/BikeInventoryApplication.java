package com.app.bikeinventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class BikeInventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(BikeInventoryApplication.class, args);
    }

}
