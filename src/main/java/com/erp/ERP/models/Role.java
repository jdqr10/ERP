package com.erp.ERP.models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "role")

public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String type;

    // Getter y Setter para id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter y Setter para type
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
