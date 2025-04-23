package com.erp.ERP.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;


@Data
@Entity
@Table(name = "report")
@Getter
@Setter
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
}
