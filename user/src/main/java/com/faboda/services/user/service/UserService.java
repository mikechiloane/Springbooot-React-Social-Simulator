package com.faboda.services.user.service;

import com.faboda.services.user.dto.UserDto;
import com.faboda.services.user.models.User;
import com.faboda.services.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private static  final Logger LOGGER = Logger.getLogger(UserService.class.getName());


    @Async
    public CompletableFuture<List<User>> getAllUsers() {
        LOGGER.info("getAllUsers");
        List<User> users = userRepository.findAll();
        return CompletableFuture.completedFuture(users);
    }

    @Async
    public CompletableFuture<User> getUser(String username) {
        LOGGER.info("getUser");
        User user = userRepository.findByUsername(username);
        if(user==null){
           return null;
        }
        return CompletableFuture.completedFuture(user);
    }

    @Async
    public CompletableFuture<User> createUser(UserDto userDto) {
        LOGGER.info("create User");
        User existingUser = userRepository.findByUsername(userDto.getUsername());
        if (existingUser == null) {
            User newUser = new User();
            newUser.setName(userDto.getName());
            newUser.setUsername(userDto.getUsername());
            User savedUser = userRepository.save(newUser);
            LOGGER.info("User Created");
            return CompletableFuture.completedFuture(savedUser);
        } else {
            LOGGER.info("User already exists");
          return CompletableFuture.completedFuture(null);
        }
    }




}
