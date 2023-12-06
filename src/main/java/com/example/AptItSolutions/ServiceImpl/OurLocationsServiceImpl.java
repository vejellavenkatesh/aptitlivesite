package com.example.AptItSolutions.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AptItSolutions.Entity.OurLocations;
import com.example.AptItSolutions.Repo.OurLocationsRepository;
import com.example.AptItSolutions.service.OurLocationsService;

@Service
public class OurLocationsServiceImpl implements OurLocationsService {

    @Autowired
    private OurLocationsRepository repository;

    @Override
    public List<OurLocations> getAllLocations() {
        return repository.findAll();
    }

    @Override
    public Optional<OurLocations> getLocationById(long id) {
        return repository.findById(id);
    }

    @Override
    public OurLocations saveLocation(OurLocations location) {
        return repository.save(location);
    }

    @Override
    public OurLocations updateLocation(long id, OurLocations updatedLocation) {
        Optional<OurLocations> existingLocation = repository.findById(id);

        if (existingLocation.isPresent()) {
            OurLocations locationToUpdate = existingLocation.get();
            locationToUpdate.setAddress(updatedLocation.getAddress()); // Update other fields as needed
            return repository.save(locationToUpdate);
        } else {
            // Handle the case where the location with the given id is not found.
            // You can throw an exception or handle it based on your requirements.
            return null;
        }
    }

    @Override
    public void deleteLocation(long id) {
        repository.deleteById(id);
    }
}
