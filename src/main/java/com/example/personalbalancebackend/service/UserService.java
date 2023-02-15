package com.example.personalbalancebackend.service;

import com.example.personalbalancebackend.entity.User;
import com.example.personalbalancebackend.exception.ResourceNotFoundException;
import com.example.personalbalancebackend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User newUser) {
        newUser.setId(UUID.randomUUID());
        newUser.setCreatedAt(new Date());
        newUser.setUpdatedAt(new Date());
        return this.userRepository.save(newUser);
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User getUserById(UUID userId) {
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }
}
