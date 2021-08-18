package com.app.bikeinventory.controller;

import com.app.bikeinventory.dto.BikeRequest;
import com.app.bikeinventory.dto.Response;
import com.app.bikeinventory.model.Bike;
import com.app.bikeinventory.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import javassist.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class InventoryController {
    static final Logger logger = LogManager.getLogger(InventoryController.class.getName());

    public static final String BIKE_ADDED_SUCCESSFULLY = "Bike added successfully";
    public static final String NEW_BIKE_ADD_START = "New Bike Add start : {}";
    public static final String BIKE_ADD_SUCCESSFUL = "Bike add successful";
    public static final String BIKE_ADD_FAILURE = "Bike add failure";
    public static final String GET_ALL_BIKES_START = "Get all bikes start";
    public static final String GET_ALL_BIKES_SUCCESS = "Get all bikes success";
    public static final String GET_ALL_BIKES_FAILURE = "Get all bikes failure";
    public static final String GET_BIKE_BY_ID_START = "Get bike by Id start : {}";
    public static final String GET_BIKE_BY_ID_SUCCESS = "Get bike by ID success";
    public static final String GET_BIKE_BY_ID_FAILURE = "Get bike by ID failure";
    public static final String DELETE_BIKE_BY_ID_START = "Delete bike by ID start : {}";
    public static final String BIKE_DELETE_SUCCESSFUL = "Bike delete successful";
    public static final String DELETE_BIKE_BY_ID_FAILURE = "Delete bike by ID failure";
    @Autowired
    private InventoryService inventoryService;

    @Operation(summary = "Bike Insert")
    @PostMapping("/bike/new")
    public ResponseEntity<Response> addBike(@RequestBody BikeRequest bikeRequest) {
        logger.info(NEW_BIKE_ADD_START,bikeRequest.getBrand());
        Response response = new Response();
        try{
            inventoryService.addBike(bikeRequest);
            logger.info(BIKE_ADD_SUCCESSFUL);
            response.setMessage(BIKE_ADDED_SUCCESSFULLY);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            logger.error(BIKE_ADD_FAILURE, e);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }

    }
    @Operation(summary = "Get all bikes")
    @GetMapping("/bike/all")
    public ResponseEntity<Response> getAllBikes(){
        logger.info(GET_ALL_BIKES_START);
        Response response = new Response();
        Iterable<Bike> bikes = null;
        try {
            bikes = inventoryService.getAllBikes();
            logger.info(GET_ALL_BIKES_SUCCESS);
            response.setBikes(bikes);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NotFoundException e) {
            response.setMessage(e.getMessage());
            logger.error(GET_ALL_BIKES_FAILURE,e);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
    @Operation(summary = "Get bike by ID")
    @GetMapping("/bike")
    public ResponseEntity<Response> getBike(@RequestParam long id){
        logger.info(GET_BIKE_BY_ID_START, id);
        Response response = new Response();
        try{
          Bike bike = inventoryService.getBike(id);
          logger.info(GET_BIKE_BY_ID_SUCCESS);
          response.setBike(bike);
          return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (NotFoundException e) {
            response.setMessage(e.getMessage());
            logger.error(GET_BIKE_BY_ID_FAILURE);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete bike by ID")
  @DeleteMapping("/bike")
    public ResponseEntity<Response> deleteBike(@RequestParam long id){
        logger.info(DELETE_BIKE_BY_ID_START, id);
        Response response = new Response();
        try{
            inventoryService.deleteBike(id);
            logger.info(BIKE_DELETE_SUCCESSFUL);
            response.setMessage(BIKE_DELETE_SUCCESSFUL);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (EmptyResultDataAccessException e){
            response.setMessage(e.getMessage());
            logger.error(DELETE_BIKE_BY_ID_FAILURE, e);
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
  }

}
