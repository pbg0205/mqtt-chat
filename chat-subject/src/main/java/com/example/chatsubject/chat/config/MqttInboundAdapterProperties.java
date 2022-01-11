package com.example.chatsubject.chat.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "mqtt.inbound")
public class MqttInboundAdapterProperties {

    private final int completionTimeOut;
    private final int qos;

}
