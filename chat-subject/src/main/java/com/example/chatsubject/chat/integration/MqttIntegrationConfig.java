package com.example.chatsubject.chat.integration;

import com.example.chatsubject.chat.integration.properties.MqttBrokerProperties;
import com.example.chatsubject.chat.integration.properties.MqttBrokerConnectionProperties;
import com.example.chatsubject.chat.dto.ChatMessageSaveRequestDTO;
import com.example.chatsubject.chat.service.ChatMessageSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Transformers;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;

@Configuration
@RequiredArgsConstructor
public class MqttIntegrationConfig {

    private final MqttBrokerProperties mqttBrokerProperties;
    private final MqttBrokerConnectionProperties mqttBrokerConnectionProperties;
    private final ChatMessageSaveService chatMessageSaveService;

    @Bean
    public IntegrationFlow chatMessageSaveFlow() {
        return IntegrationFlows.from(inboundChannel())
                .transform(Transformers.fromJson(ChatMessageSaveRequestDTO.class))
                .<ChatMessageSaveRequestDTO>handle((payload, headers) -> {
                    chatMessageSaveService.saveChatMessage(payload);
                    return payload;
                }).channel("nullChannel")
                .get();
    }

    @Bean
    public MqttPahoMessageDrivenChannelAdapter inboundChannel() {
        MqttPahoMessageDrivenChannelAdapter channelAdapter = mqttPahoMessageDrivenChannelAdapter();

        channelAdapter.setCompletionTimeout(mqttBrokerConnectionProperties.getCompletionTimeOut());
        channelAdapter.setConverter(new DefaultPahoMessageConverter());
        channelAdapter.setQos(mqttBrokerConnectionProperties.getQos());
        return channelAdapter;
    }

    @Bean
    public MqttPahoMessageDrivenChannelAdapter mqttPahoMessageDrivenChannelAdapter() {
        return new MqttPahoMessageDrivenChannelAdapter(
                mqttBrokerProperties.getBrokerUrl(),
                mqttBrokerProperties.getClientId(),
                mqttBrokerProperties.getTopicFilter());
    }

}
