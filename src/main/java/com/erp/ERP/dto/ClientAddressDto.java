package com.erp.ERP.dto;

import com.erp.ERP.models.Client;
import com.erp.ERP.models.Address;

public class ClientAddressDto {

    private Long id;
    private Client client;
    private Address address;

    // Default constructor
    public ClientAddressDto() {
    }

    // Constructor with parameters
    public ClientAddressDto(Long id, Client client, Address address) {
        this.id = id;
        this.client = client;
        this.address = address;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
