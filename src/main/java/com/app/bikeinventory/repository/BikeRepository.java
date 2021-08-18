package com.app.bikeinventory.repository;

import com.app.bikeinventory.model.Bike;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeRepository extends CrudRepository<Bike,Long> {
}
