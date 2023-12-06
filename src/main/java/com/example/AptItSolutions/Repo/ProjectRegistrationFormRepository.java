package com.example.AptItSolutions.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AptItSolutions.Entity.ProjectRegistrationForm;


@Repository
public interface ProjectRegistrationFormRepository extends JpaRepository<ProjectRegistrationForm, Long> {
  
}
