package com.app.bikeinventory.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BikeRequest {
    private String brand;
    private String color;
    private long size;
    private int gearSize;
}
