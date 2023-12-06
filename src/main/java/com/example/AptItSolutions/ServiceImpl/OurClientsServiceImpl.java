package com.example.AptItSolutions.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AptItSolutions.Entity.OurClients;
import com.example.AptItSolutions.Repo.OurClientsRepository;
import com.example.AptItSolutions.service.OurClientsService;

@Service
public class OurClientsServiceImpl implements OurClientsService {

    @Autowired
    private OurClientsRepository repository;

    @Override
    public List<OurClients> getAllClients() {
        return repository.findAll();
    }

    @Override
    public OurClients getClientById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public OurClients saveClient(OurClients client) {
        return repository.save(client);
    }

    @Override
    public OurClients updateClient(long id, OurClients updatedClient) {
        Optional<OurClients> existingClient = repository.findById(id);

        if (existingClient.isPresent()) {
            OurClients clientToUpdate = existingClient.get();
            clientToUpdate.setClientLogo(updatedClient.getClientLogo()); // Update other fields as needed
            return repository.save(clientToUpdate);
        } else {
            // Handle the case where the client with the given id is not found.
            // You can throw an exception or handle it based on your requirements.
            return null;
        }
    }

    @Override
    public void deleteClient(long id) {
        repository.deleteById(id);
    }
}
