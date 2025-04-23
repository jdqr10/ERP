package com.erp.ERP.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "role")
@Getter
@Setter
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String type;

}
