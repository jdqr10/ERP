package com.erp.ERP.dto;

import com.erp.ERP.models.Company;

public class CompanyDto {
    private Long id;
    private String name;
    private String taxId;
    private String phone;
    private String email;

    // Default constructor
    public CompanyDto() {
    }

    // Constructor
    public CompanyDto(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.taxId = company.getTaxId();
        this.phone = company.getPhone();
        this.email = company.getEmail();
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
