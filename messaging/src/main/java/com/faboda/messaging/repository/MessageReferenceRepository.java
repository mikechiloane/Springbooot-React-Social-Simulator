package com.faboda.messaging.repository;

import com.faboda.messaging.model.MessageReference;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageReferenceRepository extends MongoRepository<MessageReference, String> {

    @Query("{'messageId': ?0}")
    public MessageReference findByMessageId(String messageId);
}
