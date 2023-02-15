package com.example.personalbalancebackend.controller;

import com.example.personalbalancebackend.exception.ResourceNotFoundException;
import com.example.personalbalancebackend.mapper.UserMapper;
import com.example.personalbalancebackend.model.UserCreationDTO;
import com.example.personalbalancebackend.model.UserDTO;
import com.example.personalbalancebackend.entity.User;
import com.example.personalbalancebackend.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@Service
@RequestMapping("api/v1/users")
public class UserController {

    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public UserDTO createUser(@Valid @RequestBody UserCreationDTO userCreationDTO) {
        User user = UserMapper.INSTANCE.toEntity(userCreationDTO);
        return UserMapper.INSTANCE.toDTO(userService.createUser(user));
    }

    @GetMapping()
    public List<UserDTO> getUsers() {
        List<User> users = userService.getAllUsers();
        return UserMapper.INSTANCE.toListDTO(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID userId) throws ResourceNotFoundException {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok().body(UserMapper.INSTANCE.toDTO(user));
    }
}
