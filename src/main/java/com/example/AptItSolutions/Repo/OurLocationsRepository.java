package com.example.AptItSolutions.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AptItSolutions.Entity.OurLocations;

@Repository
public interface OurLocationsRepository extends JpaRepository<OurLocations, Long> {

    // You can add custom query methods if needed

}

