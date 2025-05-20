package com.erp.ERP.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

import lombok.*;


@Entity
@Table(name = "orders") // "order" es nombre reservado

public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate orderDate;

    @Column(nullable = false)
    private Double total;

    @Column(nullable = false)
    private String status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> items;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

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

public String getCustomerName() {
        return client != null ? client.getName() : null;
    }

    public Double getTotalAmount() {
        return total;
    }

    public void setCustomerName(String fullName) {
        if (client != null && fullName != null) {
            String[] parts = fullName.split(" ", 2); 
            client.setFirstName(parts[0]);
            client.setLastName(parts.length > 1 ? parts[1] : "");
        }
    }

    public void setTotalAmount(Double total) {
        this.total = total; 

    }
}
