package com.example.AptItSolutions.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AptItSolutions.Entity.InternshipForm;

@Repository
public interface InternshipRepo extends JpaRepository<InternshipForm, Long>{

}
