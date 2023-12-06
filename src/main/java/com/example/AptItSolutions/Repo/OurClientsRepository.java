package com.example.AptItSolutions.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AptItSolutions.Entity.OurClients;


@Repository
public interface OurClientsRepository extends JpaRepository<OurClients, Long> {

    // You can add custom query methods if needed

}
