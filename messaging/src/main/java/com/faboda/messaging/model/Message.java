package com.faboda.messaging.model;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "messages")
@RequiredArgsConstructor
@Data
@SuperBuilder
public class Message {
    @Id
    private String id;
    private String message;
    private String sender;
    private String receiver;
    private LocalDateTime createdTime;



}
