package com.erp.ERP.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.ERP.services.ClientService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import com.erp.ERP.dto.ClientDto;

@RestController
@RequestMapping("/api/protected/clients")
public class ClientController {
    
    @Autowired
    private ClientService clientService;

    //Get all clients
    @GetMapping("/getAllClients")
    public List<ClientDto> getAllClients() {
        return clientService.findAll();
    }

    //Save a new client
    @PostMapping("/createClient")
    public ClientDto createClient(@RequestBody ClientDto request) {
        return clientService.createClient(request);
    }

    //update an existing client
    @PutMapping("updateClient/{id}")
    public ClientDto updateClient(@PathVariable Long id, @RequestBody ClientDto request) {
        return clientService.updateClient(id, request);
    }

    //Delete a client by its id
    @DeleteMapping("/deleteClient/{id}")    
    public String deleteClient(@PathVariable Long id) {
        return clientService.deleteClient(id);
    }
}
