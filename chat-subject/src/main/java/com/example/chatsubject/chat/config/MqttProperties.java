package com.example.chatsubject.chat.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "mqtt.option")
public class MqttProperties {

    private final String serverUrl;
    private final String username;
    private final String password;

}
