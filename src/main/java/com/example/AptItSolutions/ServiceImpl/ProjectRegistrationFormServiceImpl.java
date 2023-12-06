package com.example.AptItSolutions.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AptItSolutions.Entity.ProjectRegistrationForm;
import com.example.AptItSolutions.Repo.ProjectRegistrationFormRepository;
import com.example.AptItSolutions.service.ProjectRegistrationFormService;

@Service
public class ProjectRegistrationFormServiceImpl implements ProjectRegistrationFormService {

    private final ProjectRegistrationFormRepository repository;

    @Autowired
    public ProjectRegistrationFormServiceImpl(ProjectRegistrationFormRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProjectRegistrationForm save(ProjectRegistrationForm form) {
        return repository.save(form);
    }

    @Override
    public List<ProjectRegistrationForm> getAll() {
        return repository.findAll();
    }

    @Override
    public ProjectRegistrationForm getById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    // You can implement additional methods as needed
}
