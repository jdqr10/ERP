package com.erp.ERP.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.ERP.dto.ClientDto;
import com.erp.ERP.models.Client;
import com.erp.ERP.repository.ClientRepository;

@Service
public class ClientService {
    
    @Autowired
    private ClientRepository clientRepository;

    //Get method to retrieve all clients
    public List<ClientDto> findAll() {
        List<ClientDto> clientToReturn = new ArrayList<>();
        List<Client> clients = clientRepository.findAll();

        for (Client client : clients) {
            ClientDto clientDto = new ClientDto();
            clientToReturn.add(clientDto);
        }

        return clientToReturn;
    }

    //Post method to create a new client
    public ClientDto createClient(ClientDto request) {
        Client client = new Client();
        client.setFirstName(request.getFirstName());
        client.setLastName(request.getLastName());
        client.setEmail(request.getEmail());
        client.setPhone(request.getPhone());

        // save the client to the database
        Client savedClient = clientRepository.save(client);

        // Convert the saved client to ClientDto
        return new ClientDto();
    }

    //Delete method to delete a client
    public String deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            return "Client with id " + id + " does not exist";
        }else{
            clientRepository.deleteById(id);
            return "Client with id " + id + " deleted successfully";
        }
    }

    //Put method to update a client
    public ClientDto updateClient(Long id, ClientDto request) {
        return clientRepository.findById(id)
                .map(client -> {
                    client.setFirstName(request.getFirstName());
                    client.setLastName(request.getLastName());
                    client.setEmail(request.getEmail());
                    client.setPhone(request.getPhone());
                    Client updatedClient = clientRepository.save(client);
                    return new ClientDto();
                })
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }
    
}
