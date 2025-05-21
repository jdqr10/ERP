package com.erp.ERP.services;

import com.erp.ERP.dto.UserDto;
import com.erp.ERP.models.User;
import com.erp.ERP.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<User> mockUsers = List.of(new User(), new User());
        when(userRepository.findAll()).thenReturn(mockUsers);

        List<UserDto> result = userService.findAll();

        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testCreateUser() {
        UserDto request = new UserDto();
        request.setUserName("jdoe");
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setEmail("jdoe@example.com");
        request.setPhoneNumber("1234567890");

        User mockUser = new User();
        mockUser.setId(1L);
        when(userRepository.save(any(User.class))).thenReturn(mockUser);

        UserDto result = userService.createUser(request);

        assertNotNull(result); // Aunque tu método devuelve un UserDto vacío
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testDeleteUser_UserExists() {
        Long id = 1L;
        when(userRepository.existsById(id)).thenReturn(true);

        String result = userService.deleteUser(id);

        assertEquals("User with id 1 deleted successfully", result);
        verify(userRepository).deleteById(id);
    }

    @Test
    void testDeleteUser_UserDoesNotExist() {
        Long id = 2L;
        when(userRepository.existsById(id)).thenReturn(false);

        String result = userService.deleteUser(id);

        assertEquals("User with id 2 does not exist", result);
    }

    @Test
    void testUpdateUser_UserExists() {
        Long id = 1L;
        UserDto request = new UserDto();
        request.setFirstName("Updated");
        request.setLastName("User");
        request.setEmail("updated@example.com");
        request.setPhoneNumber("999999999");

        User existingUser = new User();
        existingUser.setId(id);
        when(userRepository.findById(id)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(existingUser);

        UserDto result = userService.updateUser(id, request);

        assertNotNull(result);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testUpdateUser_UserNotFound() {
        Long id = 99L;
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.updateUser(id, new UserDto());
        });

        assertEquals("User not found", exception.getMessage());
    }
}
