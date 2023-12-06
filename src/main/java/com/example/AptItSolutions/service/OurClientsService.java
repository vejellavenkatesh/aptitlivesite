package com.example.AptItSolutions.service;

import java.util.List;

import com.example.AptItSolutions.Entity.OurClients;

public interface OurClientsService {

    List<OurClients> getAllClients();

    OurClients getClientById(long id);

    OurClients saveClient(OurClients client);

    OurClients updateClient(long id, OurClients updatedClient);

    void deleteClient(long id);
}
