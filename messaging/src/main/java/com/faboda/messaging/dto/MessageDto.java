package com.faboda.messaging.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MessageDto {
    public String message;
    public String sender;
    public String receiver;
}
