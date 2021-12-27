package com.example.chatsubject.chat.config;

import com.example.chatsubject.chat.domain.ChatContent;
import com.example.chatsubject.chat.domain.ChatContentRepository;
import com.example.chatsubject.chat.dto.ChatContentRequestDto;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.handler.annotation.Header;

@Configuration
@RequiredArgsConstructor
public class MqttMessageConfig {

    private final MqttProperties mqttProperties;
    private final MqttMessageConverter mqttMessageConverter;
    private final ChatContentRepository chatContentRepository;

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
    public MessageProducer mqttInboundChannel() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
                mqttProperties.getServerUrl(), "sample", mqttClientFactory(), "sampleTopic"
        );
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(mqttMessageConverter);
        adapter.setOutputChannel(mqttInputChannel());
        adapter.setQos(1);
        return adapter;
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel", outputChannel = "chatContentChannel")
    public GenericHandler<ChatContentRequestDto> chatContentHandler() {
        return (payload, headers) -> chatContentRepository.save(payload.toEntity());
    }

    @Bean
    public MessageChannel chatContentChannel() {
        return new DirectChannel();
    }

    @Bean
    @Transformer(inputChannel = "chatContentChannel", outputChannel = "messageOutputChannel")
    public GenericTransformer<ChatContent, String> chatContentTransformer() {
        return source -> {
            StringBuilder builder = new StringBuilder();
            return builder.append("[")
                    .append(source.getAuthor())
                    .append("] : ")
                    .append(source.getContent()).toString();
        };
    }

    @Bean
    public MessageChannel messageOutputChannel() {
        return new PublishSubscribeChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "messageOutputChannel")
    public MessageHandler mqttMessageHandler() {
        MqttPahoMessageHandler messageHandler
                = new MqttPahoMessageHandler("siSamplePublisher", mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic("siSampleTopic");
        return messageHandler;
    }

    @MessagingGateway(defaultRequestChannel = "messageOutputChannel")
    public interface OutboundGateway {
        void sendToMqtt(String payload, @Header(MqttHeaders.TOPIC) String topic);
    }
}
