package com.faboda.messaging.controller;

import com.faboda.messaging.dto.MessageDto;
import com.faboda.messaging.model.Message;
import com.faboda.messaging.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/v1/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping
    public String sendMessage(@RequestBody MessageDto messageDto) {
        simpMessagingTemplate.convertAndSend("/topic/message",messageDto);
        return messageService.sendMessage(messageDto).join();
    }
    @MessageMapping("/message")
    @SendTo("/topic/message")
    public MessageDto send(@Payload MessageDto messageDto){
        return messageDto;
    }

    @GetMapping("/{username}")
    public List<Message> getAllMessagesFromUser(@PathVariable String username){
        return messageService.getLastMessages(username).join();
    }

    @GetMapping("/list/{user}/{ids}")
        public HashMap<String, List<Message>> getMessagesForList(@PathVariable String user, @PathVariable String ids){
        return messageService.getMessagesForList(user,ids).join();
    }

    @GetMapping("/{sender}/{receiver}")
    public List<Message> getMessagesFromAndTo(@PathVariable String sender, @PathVariable String receiver){
        return messageService.getMessagesFromSenderToReceiver(sender, receiver).join();
    }

    @GetMapping
    public List<Message> getAllMessages(){
        return messageService.getMessages().join();
    }

}
