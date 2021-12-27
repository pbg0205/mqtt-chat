package com.example.chatsubject.chat.config;

import com.example.chatsubject.chat.dto.ChatContentRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MqttMessageConverter extends DefaultPahoMessageConverter {

    @Override
    protected Object mqttBytesToPayload(MqttMessage mqttMessage) {
        String messageString = (String) super.mqttBytesToPayload(mqttMessage);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = null;

        try {
            jsonNode = mapper.readTree(messageString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        long roomId = jsonNode.get("roomId").asLong();
        String author = jsonNode.get("author").asText();
        String content = jsonNode.get("content").asText();
        LocalDateTime createdAt = LocalDateTime.parse(jsonNode.get("createdAt").asText());

        return new ChatContentRequestDto(roomId, author, content, createdAt);
    }
}
