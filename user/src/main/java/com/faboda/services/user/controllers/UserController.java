package com.faboda.services.user.controllers;


import com.faboda.services.user.dto.UserDto;
import com.faboda.services.user.models.User;
import com.faboda.services.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers().join();
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable String username) {
        return userService.getUser(username).join();
    }

    @PostMapping
    public User createUser(@RequestBody UserDto  userDto) {
        return userService.createUser(userDto).join();
    }

}
