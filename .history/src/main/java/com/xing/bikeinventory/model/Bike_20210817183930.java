package com.app.bikeinventory.model;

import lombok.Builder;
import lombok.Data;

// @Data is a Lombok annotation that generates Getter, Setter, toString, equals and a constructor to a class
// More information here: https://projectlombok.org/features/Data
@Data

// @Builder lets you automatically produce the code required to have your class be instantiable with code such as:
// Person.builder()
//       .name("Adam Savage")
//       .city("San Francisco")
//       .job("Mythbusters")
//       .job("Unchained Reaction")
//       .build();
// More information here: https://projectlombok.org/features/Builder
@Builder
public class Bike {
    private int id;
    private String brand;
    private String color;

    //TODO: add other fields
}
