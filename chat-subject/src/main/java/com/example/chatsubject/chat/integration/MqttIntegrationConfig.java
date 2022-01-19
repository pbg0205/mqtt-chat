package com.example.chatsubject.chat.integration;

import com.example.chatsubject.chat.config.MqttConnectionProperties;
import com.example.chatsubject.chat.config.MqttInboundAdapterProperties;
import com.example.chatsubject.chat.dto.ChatMessageSaveRequest;
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

    private final MqttConnectionProperties connectionProperties;
    private final MqttInboundAdapterProperties inboundAdapterProperties;
    private final ChatMessageSaveService chatMessageSaveService;

    @Bean
    public IntegrationFlow chatMessageSaveFlow() {
        return IntegrationFlows.from(inboundChannel())
                .transform(Transformers.fromJson(ChatMessageSaveRequest.class))
                .<ChatMessageSaveRequest>handle((payload, headers) -> {
                    chatMessageSaveService.save(payload);
                    return payload;
                }).channel("nullChannel")
                .get();
    }

    @Bean
    public MqttPahoMessageDrivenChannelAdapter inboundChannel() {
        MqttPahoMessageDrivenChannelAdapter channelAdapter = new MqttPahoMessageDrivenChannelAdapter(
                connectionProperties.getBrokerUrl(),
                connectionProperties.getClientId(),
                connectionProperties.getTopicFilter());

        channelAdapter.setCompletionTimeout(inboundAdapterProperties.getCompletionTimeOut());
        channelAdapter.setConverter(new DefaultPahoMessageConverter());
        channelAdapter.setQos(inboundAdapterProperties.getQos());
        return channelAdapter;
    }

}
