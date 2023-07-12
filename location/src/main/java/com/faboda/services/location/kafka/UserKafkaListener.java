package com.faboda.services.location.kafka;

import com.faboda.services.location.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserKafkaListener {

    private static Logger logger = LoggerFactory.getLogger(UserKafkaListener.class);

    private final LocationService locationService;
    @KafkaListener(topics = {"user_topic"}, groupId = "simulator-location")
    public void consume(String user){
        logger.info("Adding new location to db");
        String[] parts = user.split(",");
        String username = parts[0].trim();
        String name = parts[1].trim();
        locationService.addNewUserToMap(username,name);

    }

}
