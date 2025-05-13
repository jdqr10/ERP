package com.erp.ERP.dto;

import java.util.Set;
import com.erp.ERP.models.ClientAddress;

public class ClientDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Set<ClientAddress> clientAddresses;

    // Default constructor
    public ClientDto() {
    }

    // Constructor
    public ClientDto(Long id, String firstName, String lastName, String email, String phone, Set<ClientAddress> clientAddresses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.clientAddresses = clientAddresses;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<ClientAddress> getClientAddresses() {
        return clientAddresses;
    }

    public void setClientAddresses(Set<ClientAddress> clientAddresses) {
        this.clientAddresses = clientAddresses;
    }

}
