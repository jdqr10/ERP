package com.erp.ERP.dto;

import java.time.LocalDate;
import java.util.List;

import com.erp.ERP.models.OrderProduct;
import com.erp.ERP.models.Client;
import com.erp.ERP.models.User;

public class OrderDto {

    private Long id;
    private LocalDate orderDate;
    private Double total;
    private String status;
    private List<OrderProduct> items;
    private Client client;
    private User user;

    // Default constructor
    public OrderDto() {
    }
    
    // Constructor
    public OrderDto(Long id, LocalDate orderDate, Double total, String status, List<OrderProduct> items, Client client, User user) {
        this.id = id;
        this.orderDate = orderDate;
        this.total = total;
        this.status = status;
        this.items = items;
        this.client = client;
        this.user = user;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderProduct> getItems() {
        return items;
    }

    public void setItems(List<OrderProduct> items) {
        this.items = items;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
