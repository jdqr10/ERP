package com.erp.ERP.controller;

import com.erp.ERP.dto.ClientDto;
import com.erp.ERP.services.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ClientControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    private ClientDto clientDto1;
    private ClientDto clientDto2;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
        objectMapper = new ObjectMapper();

        clientDto1 = new ClientDto();
        clientDto1.setId(1L);
        clientDto1.setFirstName("John");
        clientDto1.setLastName("Doe");
        clientDto1.setEmail("john.doe@example.com");
        clientDto1.setPhone("1234567890");

        clientDto2 = new ClientDto();
        clientDto2.setId(2L);
        clientDto2.setFirstName("Jane");
        clientDto2.setLastName("Smith");
        clientDto2.setEmail("jane.smith@example.com");
        clientDto2.setPhone("0987654321");
    }

    @Test
    void getAllClients_ShouldReturnAllClients() throws Exception {
        // Arrange
        List<ClientDto> clients = Arrays.asList(clientDto1, clientDto2);
        when(clientService.findAll()).thenReturn(clients);

        // Act & Assert
        mockMvc.perform(get("/api/clients/getAllClients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].firstName").value("Jane"));
    }

    @Test
    void createClient_ShouldCreateAndReturnClient() throws Exception {
        // Arrange
        when(clientService.createClient(any(ClientDto.class))).thenReturn(clientDto1);

        // Act & Assert
        mockMvc.perform(post("/api/clients/createClient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientDto1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void updateClient_ShouldUpdateAndReturnClient() throws Exception {
        // Arrange
        when(clientService.updateClient(eq(1L), any(ClientDto.class))).thenReturn(clientDto1);

        // Act & Assert
        mockMvc.perform(put("/api/clients/updateClient/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientDto1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void deleteClient_WhenClientExists_ShouldReturnSuccessMessage() throws Exception {
        // Arrange
        when(clientService.deleteClient(1L)).thenReturn("Client with id 1 deleted successfully");

        // Act & Assert
        mockMvc.perform(delete("/api/clients/deleteClient/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Client with id 1 deleted successfully"));
    }

    @Test
    void deleteClient_WhenClientNotExists_ShouldReturnNotFoundMessage() throws Exception {
        // Arrange
        when(clientService.deleteClient(99L)).thenReturn("Client with id 99 does not exist");

        // Act & Assert
        mockMvc.perform(delete("/api/clients/deleteClient/99"))
                .andExpect(status().isOk())
                .andExpect(content().string("Client with id 99 does not exist"));
    }
}