package com.faboda.messaging.repository;

import com.faboda.messaging.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

    @Query("{'$or': [{'receiver': ?0}, {'sender': ?0}]}")
    List<Message> findMessagesByReceiver(String receiver);
    @Query("{'$or': [{'$and': [{'sender': ?0}, {'receiver': ?1}]},{'$and': [{'sender': ?1}, {'receiver': ?0}]}]}")
    List<Message> findMessegesForTwo(String sender, String receiver);


}
