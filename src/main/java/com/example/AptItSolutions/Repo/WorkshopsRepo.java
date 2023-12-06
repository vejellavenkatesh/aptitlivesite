package com.example.AptItSolutions.Repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AptItSolutions.Entity.WorkshopReg;


@Repository
public interface WorkshopsRepo extends JpaRepository<WorkshopReg, Long> {
  
}
