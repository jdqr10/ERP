package com.erp.ERP.dto;

import java.util.HashSet;
import java.util.Set;
import com.erp.ERP.models.ClientAddress;

public class AddressDto {

    private Long id;
    private String address;
    private Set<ClientAddress> clientAddresses = new HashSet<>();

    // Default constructor
    public AddressDto() {
    }

    // Constructor
    public AddressDto(Long id, String address, Set<ClientAddress> clientAddresses) {
        this.id = id;
        this.address = address;
        this.clientAddresses = clientAddresses;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<ClientAddress> getClientAddresses() {
        return clientAddresses;
    }

    public void setClientAddresses(Set<ClientAddress> clientAddresses) {
        this.clientAddresses = clientAddresses;
    }
}
