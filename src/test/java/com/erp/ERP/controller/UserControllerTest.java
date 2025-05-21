package com.erp.ERP.controller;

import com.erp.ERP.dto.UserDto;
import com.erp.ERP.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false) // Disable security filters for testing
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testGetAllUsers() throws Exception {
        UserDto user1 = new UserDto(1L, "jdoe", "password", "jdoe@example.com", "1234567890", "John", "Doe");
        UserDto user2 = new UserDto(2L, "asmith", "password", "asmith@example.com", "0987654321", "Alice", "Smith");

        Mockito.when(userService.findAll()).thenReturn(Arrays.asList(user1, user2));

        mockMvc.perform(get("/api/protected/users/getAllUsers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].userName", is("jdoe")))
                .andExpect(jsonPath("$[1].userName", is("asmith")));
    }

    @Test
    void testCreateUser() throws Exception {
        UserDto input = new UserDto(null, "jdoe", "password", "jdoe@example.com", "1234567890", "John", "Doe");
        UserDto saved = new UserDto(1L, "jdoe", "password", "jdoe@example.com", "1234567890", "John", "Doe");

        Mockito.when(userService.createUser(any(UserDto.class))).thenReturn(saved);

        mockMvc.perform(post("/api/protected/users/createUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.userName", is("jdoe")));
    }

    @Test
    void testUpdateUser() throws Exception {
        UserDto input = new UserDto(null, "jdoe", "password", "newemail@example.com", "1234567890", "John", "Doe");
        UserDto updated = new UserDto(1L, "jdoe", "password", "newemail@example.com", "1234567890", "John", "Doe");

        Mockito.when(userService.updateUser(Mockito.eq(1L), any(UserDto.class))).thenReturn(updated);

        mockMvc.perform(put("/api/protected/users/updateUser/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is("newemail@example.com")));
    }

    @Test
    void testDeleteUser() throws Exception {
        Mockito.when(userService.deleteUser(1L)).thenReturn("User with id 1 deleted successfully");

        mockMvc.perform(delete("/api/protected/users/deleteUser/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("User with id 1 deleted successfully"));
    }
}
