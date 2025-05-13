package com.erp.ERP.dto;

import com.erp.ERP.models.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class RoleDto {

    private Long id;
    private String type;

    public RoleDto(){
    } 
    
    public RoleDto(Role role) {
        this.id = role.getId();
        this.type = role.getType();
    }

    // Getter and Setter for ID
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
