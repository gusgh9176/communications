package com.example.communications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;


@EnableMongoAuditing
@SpringBootApplication
public class CommunicationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunicationsApplication.class, args);
    }

}
