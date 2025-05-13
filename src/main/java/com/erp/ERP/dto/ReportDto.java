package com.erp.ERP.dto;

public class ReportDto {

    private Long id;
    private String name;
    private String description;
    private String type;
    private String date;

    // Default constructor
    public ReportDto() {
    }

    // Constructor
    public ReportDto(Long id, String name, String description, String type, String date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.date = date;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
