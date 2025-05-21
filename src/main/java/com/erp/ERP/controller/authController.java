package com.erp.ERP.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.erp.ERP.services.AuthService;

import lombok.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class authController {

   private final AuthService authService;

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> login (@RequestBody LoginRequestController request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<AuthResponse> register (@RequestBody RegisterRequestController request) {
        return ResponseEntity.ok(authService.register(request));
    }

}