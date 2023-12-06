package com.example.AptItSolutions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.AptItSolutions.Entity.OurLocations;
import com.example.AptItSolutions.service.OurLocationsService;

@RestController
@RequestMapping("/api/locations")
public class OurLocationsController {

    @Autowired
    private OurLocationsService locationService;

    @GetMapping
    public List<OurLocations> getAllLocations() {
        return locationService.getAllLocations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OurLocations> getLocationById(@PathVariable long id) {
        OurLocations location = locationService.getLocationById(id).orElse(null);
        return location != null
                ? ResponseEntity.ok(location)
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/save")
    public ResponseEntity<OurLocations> saveLocation(
            @RequestParam String city,
            @RequestParam String address) {

        // Create an OurLocations object
        OurLocations location = new OurLocations();
        location.setCity(city);
        location.setAddress(address);

        // Perform any validation or business logic if needed

        // Save the location
        OurLocations savedLocation = locationService.saveLocation(location);

        return new ResponseEntity<>(savedLocation, HttpStatus.CREATED);
    }


    @PutMapping("/updatelocation/{id}")
    public ResponseEntity<OurLocations> updateLocation(
            @PathVariable long id,
            @RequestParam String city,
            @RequestParam String address
    ) {
        OurLocations updatedLocation = new OurLocations();
        updatedLocation.setId(id);  // Assuming id is part of the OurLocations class

        // Set other properties based on the request parameters
        updatedLocation.setCity(city);
        updatedLocation.setAddress(address);

        OurLocations result = locationService.updateLocation(id, updatedLocation);
        return result != null
                ? ResponseEntity.ok(result)
                : ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable long id) {
        locationService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }
}
