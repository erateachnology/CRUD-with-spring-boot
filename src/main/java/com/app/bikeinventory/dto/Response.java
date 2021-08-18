package com.app.bikeinventory.dto;

import com.app.bikeinventory.model.Bike;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private String message;
    private Iterable<Bike> bikes;
    private Bike bike;
}
