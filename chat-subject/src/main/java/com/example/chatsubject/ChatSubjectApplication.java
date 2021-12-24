package com.example.chatsubject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ChatSubjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatSubjectApplication.class, args);
    }

}
