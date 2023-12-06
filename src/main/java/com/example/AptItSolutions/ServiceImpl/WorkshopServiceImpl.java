package com.example.AptItSolutions.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AptItSolutions.Entity.WorkshopReg;
import com.example.AptItSolutions.Repo.WorkshopsRepo;
import com.example.AptItSolutions.service.WorkshopService;



@Service
public class WorkshopServiceImpl implements WorkshopService {

	@Autowired
    private  WorkshopsRepo workshopsRepo;


	@Override
	public WorkshopReg save(WorkshopReg form) {
		// TODO Auto-generated method stub
		return workshopsRepo.save(form);
	}


	@Override
	public List<WorkshopReg> getAll() {
		// TODO Auto-generated method stub
		return workshopsRepo.findAll();
	}


	@Override
	public WorkshopReg getById(long id) {
		// TODO Auto-generated method stub
		return workshopsRepo.findById(null).orElse(null);
	}


	@Override
	public void deleteById(long id) {
		workshopsRepo.deleteById(null);
		
	}

	

 
}
