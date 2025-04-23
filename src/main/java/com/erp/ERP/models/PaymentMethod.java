package com.erp.ERP.models;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;


@Data
@Entity
@Table(name = "payment_method")
@Getter
@Setter
public class PaymentMethod {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "paymentMethod")
    private List<Payment> payments;
}
