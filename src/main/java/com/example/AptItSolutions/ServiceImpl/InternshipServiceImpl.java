package com.example.AptItSolutions.ServiceImpl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AptItSolutions.Entity.InternshipForm;
import com.example.AptItSolutions.Repo.InternshipRepo;
import com.example.AptItSolutions.service.InternshipService;

@Service
public class InternshipServiceImpl implements InternshipService {

    @Autowired
    private InternshipRepo internshipRepo;

    @Override
    public InternshipForm save(InternshipForm internshipForm) {
        // TODO: Implement validation or other logic before saving
        return internshipRepo.save(internshipForm);
    }

    @Override
    public List<InternshipForm> getAll() {
        return internshipRepo.findAll();
    }

    @Override
    public InternshipForm getById(long id) {
        return internshipRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteById(long id) {
        internshipRepo.deleteById(id);
    }
}
