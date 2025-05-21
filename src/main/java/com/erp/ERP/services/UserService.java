package com.erp.ERP.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.erp.ERP.repository.UserRepository;
import com.erp.ERP.models.User;
import com.erp.ERP.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Get method to retrieve all users
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                    .map(UserDto::new)
                    .collect(Collectors.toList());
    }

    // Post method to create a new User
    public UserDto createUser(UserDto request) {
        User user = new User();
        user.setUserName(request.getUserName());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhoneNumber());
        user.setPassword(request.getPassword());

        User savedUser = userRepository.save(user);
        return new UserDto(savedUser);
    }

    // Delete method to delete a user by id
    public String deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            return "User with id " + id + " does not exist";
        } else {
            userRepository.deleteById(id);
            return "User with id " + id + " deleted successfully";
        }
    }

    // Update method to update a user by id
    public UserDto updateUser(Long id, UserDto request) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setFirstName(request.getFirstName());
                    user.setLastName(request.getLastName());
                    user.setEmail(request.getEmail());
                    user.setPhone(request.getPhoneNumber());
                    user.setPassword(request.getPassword());
                    User updatedUser = userRepository.save(user);
                    return new UserDto(updatedUser);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}