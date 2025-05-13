package com.erp.ERP.dto;

import java.time.LocalDate;

import com.erp.ERP.models.Order;
import com.erp.ERP.models.PaymentMethod;

public class PaymentDto {

    private Long id;
    private Double amount;
    private String status;
    private LocalDate paymentDate;
    private Order order;
    private PaymentMethod paymentMethod;

    // Default constructor
    public PaymentDto() {
    }
    
    // Constructor
    public PaymentDto(Long id, Double amount, String status, LocalDate paymentDate, Order order, PaymentMethod paymentMethod) {
        this.id = id;
        this.amount = amount;
        this.status = status;
        this.paymentDate = paymentDate;
        this.order = order;
        this.paymentMethod = paymentMethod;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
