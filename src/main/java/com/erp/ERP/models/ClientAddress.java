package com.erp.ERP.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "client_address")
@Getter
@Setter
public class ClientAddress {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client_address")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
}
