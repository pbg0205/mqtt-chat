package com.example.chatsubject.chat.integration;


import com.example.chatsubject.chat.domain.ChatRoom;
import com.example.chatsubject.chat.domain.ChatRoomRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MqttIntegrationConfigTest {

    @Autowired
    private IntegrationFlow chatMessageSaveFlow;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Test
    @Transactional
    @DisplayName("채팅 메세지 전송할 경우, 서버에서 채팅 메세지를 저장한다.")
    void chatMessageSaveFlowTest() {
        //given
        String json = "{" +
                "\"writerName\":\"cooper\"," +
                "\"message\":\"hi\"," +
                "\"messageType\":\"CHAT\"," +
                "\"createdAt\":\"2022-01-13T01:30:30\"," +
                "\"chatRoomId\": 1" +
                "}";

        Message<String> chatMessageMQTT = MessageBuilder.withPayload(json)
                .setHeader(MqttHeaders.RECEIVED_TOPIC, "topic")
                .build();

        //when
        chatMessageSaveFlow.getInputChannel().send(chatMessageMQTT);
        ChatRoom chatRoom = chatRoomRepository.findById(1L).orElseThrow(IllegalArgumentException::new);
        int messageCount = chatRoom.getChatMessages().size();

        //then
        Assertions.assertThat(messageCount).isPositive();
    }

}
