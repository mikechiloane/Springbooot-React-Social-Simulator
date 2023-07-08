package com.faboda.messaging.controller;


import com.faboda.messaging.dto.MessageDto;
import com.faboda.messaging.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MessageSocketController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageService messageService;


    @PostMapping
    public String sendMessage(@RequestBody MessageDto messageDto) {
        return messageService.sendMessage(messageDto).join();
    }


}
