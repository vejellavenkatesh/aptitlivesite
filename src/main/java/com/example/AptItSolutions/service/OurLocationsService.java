package com.example.AptItSolutions.service;

import java.util.List;
import java.util.Optional;

import com.example.AptItSolutions.Entity.OurLocations;

public interface OurLocationsService {

    List<OurLocations> getAllLocations();

    Optional<OurLocations> getLocationById(long id);

    OurLocations saveLocation(OurLocations location);

    OurLocations updateLocation(long id, OurLocations updatedLocation);

    void deleteLocation(long id);
}
