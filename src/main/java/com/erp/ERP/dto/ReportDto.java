package com.erp.ERP.dto;

import java.time.LocalDate;

public class ReportDto {
    private Long id;
    private LocalDate generationDate;
    private String filePath;
    private String type;
    private Long clientId;
    private Long userId;
    private Long orderId;

    // Constructor
    public ReportDto() {
    }

    // Constructor with parameters
    public ReportDto(Long id, LocalDate generationDate, String filePath, String type, Long clientId, Long userId, Long orderId) {
        this.id = id;
        this.generationDate = generationDate;
        this.filePath = filePath;
        this.type = type;
        this.clientId = clientId;
        this.userId = userId;
        this.orderId = orderId;
    }


    // Getters y Setters

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

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
