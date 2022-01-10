package com.example.chatsubject.chat.integration;

import com.example.chatsubject.chat.config.MqttConnectionProperties;
import com.example.chatsubject.chat.domain.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Transformers;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageHandler;

@Configuration
@RequiredArgsConstructor
public class MqttIntegrationConfig {

    private final MqttConnectionProperties connectionProperties;

    @Bean
    public IntegrationFlow chatMessageSaveFlow() {
        return IntegrationFlows.from(inboundChannel())
                .transform(Transformers.fromJson(ChatMessage.class))
                .handle(chatMessageSaveHandler())
                .get();
    }

    @Bean
    public MqttPahoMessageDrivenChannelAdapter inboundChannel() {
        MqttPahoMessageDrivenChannelAdapter channelAdapter = new MqttPahoMessageDrivenChannelAdapter(
                connectionProperties.getBrokerUrl(),
                connectionProperties.getClientId(),
                connectionProperties.getTopicFilter());

        channelAdapter.setCompletionTimeout(5000);
        channelAdapter.setConverter(new DefaultPahoMessageConverter());
        channelAdapter.setQos(1);
        return channelAdapter;
    }

    @Bean
    public MessageHandler chatMessageSaveHandler() {
        return message -> System.out.println(message.getPayload());
    }
}
