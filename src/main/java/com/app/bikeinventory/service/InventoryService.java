package com.app.bikeinventory.service;
import com.app.bikeinventory.dto.BikeRequest;
import com.app.bikeinventory.model.Bike;

import com.app.bikeinventory.repository.BikeRepository;
import javassist.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
public class InventoryService {
    static final Logger logger = LogManager.getLogger(InventoryService.class.getName());
    public static final String NO_BIKES_FOUND = "No bikes found";
    public static final String NO_BIKE_FOUND_FOR_THIS_ID = "No Bike found for this ID";
    public static final String BIKES = "bikes : {}";

    @Autowired
    private BikeRepository bikeRepository;

    public void addBike(BikeRequest bikeRequest){
        Bike bike = new Bike();
        bike.setBrand(bikeRequest.getBrand());
        bike.setColor(bikeRequest.getColor());
        bike.setGearSize(bikeRequest.getGearSize());
        bike.setSize(bikeRequest.getSize());
        bikeRepository.save(bike);
    }

    public Iterable<Bike> getAllBikes() throws NotFoundException {
        Iterable<Bike> bikes =  bikeRepository.findAll();

        if(bikes.iterator().hasNext()){
            logger.debug(BIKES, bikes  );
            return bikes;
        }else {
            throw new NotFoundException(NO_BIKES_FOUND);
        }
    }

    public Bike getBike(long id) throws NotFoundException {
        Optional<Bike> bike = bikeRepository.findById(id);
        if(!bike.isPresent()){
            throw new NotFoundException(NO_BIKE_FOUND_FOR_THIS_ID);
        }
        logger.debug(BIKES, bike.get());
        return bike.get();
    }

   public void deleteBike(long id) throws EmptyResultDataAccessException {
        bikeRepository.deleteById(id);
   }
}
