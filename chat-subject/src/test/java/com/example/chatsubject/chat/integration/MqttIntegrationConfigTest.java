package com.example.chatsubject.chat.integration;


import com.example.chatsubject.chat.domain.ChatRoom;
import com.example.chatsubject.chat.domain.ChatRoomRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
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
    void chatMessageSaveFlowTest() throws JSONException {
        //given
        JSONObject messageJson = new JSONObject();
        messageJson.put("writerName", "cooper");
        messageJson.put("message", "hi");
        messageJson.put("messageType", "CHAT");
        messageJson.put("createdAt", "2022-01-13T01:30:30");
        messageJson.put("chatRoomId", 1L);

        Message<String> chatMessageMQTT = MessageBuilder.withPayload(messageJson.toString())
                .setHeader(MqttHeaders.RECEIVED_TOPIC, "topic")
                .build();

        //when
        chatMessageSaveFlow.getInputChannel().send(chatMessageMQTT);
        ChatRoom chatRoom = chatRoomRepository.findById(1L).orElseThrow(IllegalArgumentException::new);
        int messageCount = chatRoom.getChatMessages().size();

        //then
        Assertions.assertThat(messageCount).isNotZero();
    }

}
