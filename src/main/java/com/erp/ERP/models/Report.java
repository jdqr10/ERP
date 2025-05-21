package com.erp.ERP.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

@Entity
@Table(name = "report")

public class Report {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "generation_date", nullable = false)
    private LocalDate generationDate;

    @Column(name = "file_path", nullable = false)
    private String filePath;

    @Column(name = "type", nullable = false)
    private String type;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    //Getters and Setters

    public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public LocalDate getGenerationDate() {
    return generationDate;
}

public void setGenerationDate(LocalDate generationDate) {
    this.generationDate = generationDate;
}

public String getFilePath() {
    return filePath;
}

public void setFilePath(String filePath) {
    this.filePath = filePath;
}

public String getType() {
    return type;
}

public void setType(String type) {
    this.type = type;
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

public Order getOrder() {
    return order;
}

public void setOrder(Order order) {
    this.order = order;
}

}
