package com.erp.ERP.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.ERP.dto.RoleDto;
import com.erp.ERP.models.Role;
import com.erp.ERP.repository.RoleRepository;

import jakarta.security.auth.message.callback.PrivateKeyCallback;

@Service
public class RolService {
    
    @Autowired
    private RoleRepository roleRepository;

    // Get method to fetch all roles
    public List<RoleDto> findAll() {
        List<RoleDto> roleToReturn = new ArrayList<>();
        List<Role> roles = roleRepository.findAll();

        //Convert each Role entity to RoleDto
        for (Role role : roles) {
            RoleDto roleDtoGet = new RoleDto(role);
            roleToReturn.add(roleDtoGet);
        }
        return roleToReturn;
    }

    // Post method to create a new role
    public RoleDto createRole(RoleDto request) {
        Role role = new Role();
        role.setType(request.getType());

        // save the role to the database
        Role savedRole = roleRepository.save(role);

        // Convert the saved role to RoleDto
        return new RoleDto(savedRole);
    }
    
}
