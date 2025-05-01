package com.erp.ERP.controller;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class authController {

    @PostMapping(value = "/login")
    public String login () {
        return "Login from public endpoint";
    }

    @PostMapping(value = "/register")
    public String register () {
        return "Register from public endpoint";
    }

}