package com.faboda.messaging.service;

import com.faboda.messaging.dto.MessageReferenceDto;
import com.faboda.messaging.model.Message;
import com.faboda.messaging.model.MessageReference;
import com.faboda.messaging.repository.MessageReferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class MessageReferenceService {

    private final MessageReferenceRepository messageReferenceRepository;


    @Async
    public CompletableFuture<MessageReference> saveMessageReference(MessageReferenceDto messageReferenceDto) {
        MessageReference messageReference = new MessageReference();

        messageReference.setMessageId(messageReferenceDto.getMessageId());
        messageReference.setFrom(messageReferenceDto.getFrom());
        messageReference.setTo(messageReferenceDto.getTo());
        messageReferenceRepository.save(messageReference);

        return CompletableFuture.completedFuture(messageReference);
    }


}
