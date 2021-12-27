package com.example.chatsubject.chat.config;

import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;

@Configuration
@RequiredArgsConstructor
public class MqttMessageConfig {

    private final MqttProperties mqttProperties;

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(mqttConnectionOptions());
        return factory;
    }

    @Bean
    public MqttConnectOptions mqttConnectionOptions() {
        MqttConnectOptions options = new MqttConnectOptions();

        options.setServerURIs(new String[]{mqttProperties.getServerUrl()});
        options.setUserName(mqttProperties.getUsername());
        options.setPassword(mqttProperties.getPassword().toCharArray());

        return options;
    }

    @Bean
    public MessageProducer inboundChannel() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
                mqttProperties.getServerUrl(),
                "sample",
                mqttClientFactory(),
                "sampleTopic"
        );
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());

        //TODO OutputChannelName 설정하기
        //adapter.setOutputChannelName();
        adapter.setQos(1);
        return adapter;
    }
}
