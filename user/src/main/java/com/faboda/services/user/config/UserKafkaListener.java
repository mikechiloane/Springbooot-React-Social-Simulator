package com.faboda.services.user.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserKafkaListener {

    private static Logger logger = LoggerFactory.getLogger(UserKafkaListener.class);

    @KafkaListener(topics = {"user_topic"}, groupId = "simulator")
    public void consume(String user){
        logger.info(user);
    }

}
