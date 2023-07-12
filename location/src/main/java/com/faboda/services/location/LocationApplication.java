package com.faboda.services.location;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class LocationApplication {


	private static final Logger logger = LoggerFactory.getLogger(LocationApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(LocationApplication.class, args);
	}


}
