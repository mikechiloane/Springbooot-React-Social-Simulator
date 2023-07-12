package com.faboda.services.user.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@SuperBuilder
public class User {

    @Id
    private String id;
    private String username;
    private String name;


}
