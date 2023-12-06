package com.example.AptItSolutions.service;

import java.util.List;

import com.example.AptItSolutions.Entity.WorkshopReg;

public interface WorkshopService {
	WorkshopReg save(WorkshopReg form);
    List<WorkshopReg> getAll();
    WorkshopReg getById(long id);
    void deleteById(long id);
    
}
