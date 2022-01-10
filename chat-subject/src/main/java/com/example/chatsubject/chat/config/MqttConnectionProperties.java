package com.example.chatsubject.chat.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "mqtt.connection")
public class MqttConnectionProperties {

    private final String brokerUrl;
    private final String clientId;
    private final String topicFilter;

}
