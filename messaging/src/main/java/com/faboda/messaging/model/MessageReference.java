package com.faboda.messaging.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "messages-references")
@RequiredArgsConstructor
@Data
@SuperBuilder
public class MessageReference {

    @Id
    private String id;
    private String messageId;
    private String from;
    private String to;
}
