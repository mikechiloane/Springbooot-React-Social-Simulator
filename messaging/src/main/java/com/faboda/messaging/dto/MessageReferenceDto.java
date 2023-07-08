package com.faboda.messaging.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MessageReferenceDto {
    private String messageId;
    private String from;
    private String to;
}
