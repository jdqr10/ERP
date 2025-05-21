package com.erp.ERP.services;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.erp.ERP.repository.UserRepository;

import com.erp.ERP.repository.RoleRepository;
import com.erp.ERP.controller.AuthResponse;
import lombok.RequiredArgsConstructor;

import com.erp.ERP.controller.*;
import com.erp.ERP.models.User;
import com.erp.ERP.models.Role;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login (LoginRequestController request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getName(), request.getPassword()));
        UserDetails user = (UserDetails) userRepository.findByName(request.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register (RegisterRequestController request) {

        Role role = roleRepository.findById(request.getRole_id())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = User.builder()
                .userName(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(role)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken((UserDetails) user))
                .build();
    }

}