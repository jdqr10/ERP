package com.erp.ERP.controller;

import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.erp.ERP.dto.UserDto;
import java.util.List;

import com.erp.ERP.services.UserService;

@RestController
@RequestMapping("api/protected/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;


    //Get all clients
    @GetMapping("/getAllUsers")
    public List<UserDto> getAllUsers() {
        return userService.findAll();
    }

    //Save a new client
    @PostMapping("/createUser")
    public UserDto createClient(@io.swagger.v3.oas.annotations.parameters.RequestBody UserDto request) {
        return userService.createUser(request);
    }

    //update an existing client
    @PutMapping("updateUser/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto request) {
        return userService.updateUser(id, request);
    }

    //Delete a client by its id
    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
        return userService.deleteClient(id);
    }

};