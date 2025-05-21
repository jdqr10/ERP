package com.erp.ERP.controller;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestController {
    
    String name;
    String password;

};