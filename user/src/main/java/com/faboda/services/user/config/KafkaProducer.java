package com.faboda.services.user.config;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String data) {
        if (topic == null || topic.isEmpty()) {
            throw new IllegalArgumentException("Topic cannot be null or empty.");
        }

        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null.");
        }

        try {
            LOGGER.info("Sending data='{}' to topic='{}'", data, topic);
            kafkaTemplate.send(topic, data);
        } catch (Exception e) {
            LOGGER.error("Failed to send data to Kafka. Topic: '{}', Data: '{}'", topic, data, e);
        }
    }
}
