package com.example.chatsubject.chat.integration.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "mqtt.broker")
public class MqttBrokerProperties {

    private final String brokerUrl;
    private final String clientId = MqttClient.generateClientId();
    private final String[] topicFilter;

}
