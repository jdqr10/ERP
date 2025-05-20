package com.erp.ERP.services;

import com.erp.ERP.dto.ClientDto;
import com.erp.ERP.models.Client;
import com.erp.ERP.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    private Client client1;
    private Client client2;
    private ClientDto clientDto1;

    @BeforeEach
    void setUp() {
        client1 = new Client();
        client1.setId(1L);
        client1.setFirstName("John");
        client1.setLastName("Doe");
        client1.setEmail("john.doe@example.com");
        client1.setPhone("1234567890");

        client2 = new Client();
        client2.setId(2L);
        client2.setFirstName("Jane");
        client2.setLastName("Smith");
        client2.setEmail("jane.smith@example.com");
        client2.setPhone("0987654321");

        clientDto1 = new ClientDto();
        clientDto1.setFirstName("John");
        clientDto1.setLastName("Doe");
        clientDto1.setEmail("john.doe@example.com");
        clientDto1.setPhone("1234567890");
    }

    @Test
    void findAll_ShouldReturnAllClients() {
        // Arrange
        when(clientRepository.findAll()).thenReturn(Arrays.asList(client1, client2));

        // Act
        List<ClientDto> result = clientService.findAll();

        // Assert
        assertEquals(2, result.size());
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    void createClient_ShouldSaveAndReturnClientDto() {
        // Arrange
        when(clientRepository.save(any(Client.class))).thenReturn(client1);

        // Act
        ClientDto result = clientService.createClient(clientDto1);

        // Assert
        assertNotNull(result);
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void deleteClient_WhenClientExists_ShouldDeleteAndReturnSuccessMessage() {
        // Arrange
        Long clientId = 1L;
        when(clientRepository.existsById(clientId)).thenReturn(true);

        // Act
        String result = clientService.deleteClient(clientId);

        // Assert
        assertEquals("Client with id " + clientId + " deleted successfully", result);
        verify(clientRepository, times(1)).deleteById(clientId);
    }

    @Test
    void deleteClient_WhenClientNotExists_ShouldReturnNotFoundMessage() {
        // Arrange
        Long clientId = 99L;
        when(clientRepository.existsById(clientId)).thenReturn(false);

        // Act
        String result = clientService.deleteClient(clientId);

        // Assert
        assertEquals("Client with id " + clientId + " does not exist", result);
        verify(clientRepository, never()).deleteById(clientId);
    }

    @Test
    void updateClient_WhenClientExists_ShouldUpdateAndReturnClientDto() {
        // Arrange
        Long clientId = 1L;
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client1));
        when(clientRepository.save(any(Client.class))).thenReturn(client1);

        // Act
        ClientDto result = clientService.updateClient(clientId, clientDto1);

        // Assert
        assertNotNull(result);
        verify(clientRepository, times(1)).findById(clientId);
        verify(clientRepository, times(1)).save(client1);
    }

    @Test
    void updateClient_WhenClientNotExists_ShouldThrowException() {
        // Arrange
        Long clientId = 99L;
        when(clientRepository.findById(clientId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            clientService.updateClient(clientId, clientDto1);
        });
        verify(clientRepository, never()).save(any(Client.class));
    }
}