package com.erp.ERP.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.erp.ERP.repository.UserRepository;

import com.erp.ERP.models.User;
import com.erp.ERP.dto.UserDto;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //Get method to retrieve all clients
    public List<UserDto> findAll() {
        List<UserDto> userToReturn = new ArrayList<>();
        List<User> users = userRepository.findAll();

        for (User user : users) {
            UserDto userDto = new UserDto();
            userToReturn.add(userDto);
        }

        return userToReturn;
    }

    public UserDto createUser(UserDto request) {
        User user = new User();
        user.setUserName(request.getUsername());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhoneNumber());

        // save the client to the database
        User savedUser = userRepository.save(user);

        // Convert the saved client to ClientDto
        return new UserDto();
    }

    public String deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            return "User with id " + id + " does not exist";
        }else{
            userRepository.deleteById(id);
            return "User with id " + id + " deleted successfully";
        }
    }

    public UserDto updateUser(Long id, UserDto request) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setFirstName(request.getFirstName());
                    user.setLastName(request.getLastName());
                    user.setEmail(request.getEmail());
                    user.setPhone(request.getPhoneNumber());
                    User updatedUser = userRepository.save(user);
                    return new UserDto();
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

}