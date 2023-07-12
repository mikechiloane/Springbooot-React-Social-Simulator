package com.faboda.services.user.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserDto {
    private String username;
    private String name;
}
