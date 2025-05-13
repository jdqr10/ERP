package com.erp.ERP.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.erp.ERP.services.RolService;
import com.erp.ERP.dto.RoleDto;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    
    @Autowired
    private RolService rolService;

    //Retrieve all roles
    @GetMapping("/getRoles")
    public List<RoleDto> getAllRoles() {
        return rolService.findAll();
    }

    //Save a new role
    @PostMapping("/createRole")
    public RoleDto createRole(@RequestBody RoleDto request) {
        return rolService.createRole(request);
    }
   
}
