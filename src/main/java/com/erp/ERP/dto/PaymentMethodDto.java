package com.erp.ERP.dto;

import java.util.List;
import com.erp.ERP.models.Payment;

public class PaymentMethodDto {

    private Long id;
    private String name;
    private List<Payment> payments;

    // Default constructor
    public PaymentMethodDto() {
    }
    
    // Constructor
    public PaymentMethodDto(Long id, String name, List<Payment> payments) {
        this.id = id;
        this.name = name;
        this.payments = payments;
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

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}
