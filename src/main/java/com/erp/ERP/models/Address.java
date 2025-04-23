package com.erp.ERP.models;

import jakarta.persistence.*;
import java.util.*;
import lombok.*;

@Data
@Entity
@Table(name = "address")
@Getter
@Setter
public class Address {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address", nullable = false)
    private String address;

    @ManyToMany(mappedBy = "addresses")
    private Set<Client> clients = new HashSet<>();
    
}
