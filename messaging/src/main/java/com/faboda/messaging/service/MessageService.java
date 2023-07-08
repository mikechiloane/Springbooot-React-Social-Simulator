package com.faboda.messaging.service;

import com.faboda.messaging.dto.MessageDto;
import com.faboda.messaging.model.Message;
import com.faboda.messaging.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    @Async
    public CompletableFuture<String> sendMessage(MessageDto messageDto) {
        Message message = Message.builder()
                .message(messageDto.getMessage())
                .sender(messageDto.getSender())
                .createdTime(LocalDateTime.now())
                .receiver(messageDto.getReceiver())
                .build();
        var saveAttempt = messageRepository.save(message).getId();
        return CompletableFuture.completedFuture(saveAttempt);
    }

    @Async
    public CompletableFuture<List<Message>> getMessagesFromSenderToReceiver(String sender, String receiver){
        return  CompletableFuture.completedFuture(messageRepository.findMessegesForTwo(sender,receiver));
    }
    @Async
    public CompletableFuture<List<Message>> getMessages() {
        return CompletableFuture.completedFuture(messageRepository.findAll());
    }


    @Async
    public CompletableFuture<HashMap<String,List<Message>>> getMessagesForList(String user, String ids) {
        List<String> idsToList = Arrays.asList(ids.split(","));
        List<Message> lastMessages = new ArrayList<Message>();
        HashMap<String, List<Message>> messagesMap = new HashMap<>();

        for(String id: idsToList){
            List<Message> messages = CompletableFuture.completedFuture(messageRepository.findMessegesForTwo(user,id)).join();
            messages.sort(Comparator.comparing(Message::getCreatedTime).reversed());
            messagesMap.put(id,messages);
        }

        return CompletableFuture.completedFuture(messagesMap);

    }


    @Async
    public CompletableFuture<List<Message>> getLastMessages(String username) {

        List<Message> allReceiverMessages = messageRepository.findMessagesByReceiver(username);
        return CompletableFuture.completedFuture(allReceiverMessages);
    }



}
