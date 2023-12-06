package com.example.AptItSolutions.service;

import java.util.List;

import com.example.AptItSolutions.Entity.InternshipForm;

public interface InternshipService {
	
	
	InternshipForm save(InternshipForm internshipForm);
    List<InternshipForm> getAll();
    InternshipForm getById(long id);
    void deleteById(long id);
    

}
