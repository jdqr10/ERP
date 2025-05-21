package com.erp.ERP.controller;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestController {
    
    String name;
    String email;
    String password;
    private Long role_id;

}
