package com.example.chatsubject.chat.integration.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "mqtt.connection")
public class MqttBrokerConnectionProperties {

    private final int completionTimeOut;
    private final int qos;

}
