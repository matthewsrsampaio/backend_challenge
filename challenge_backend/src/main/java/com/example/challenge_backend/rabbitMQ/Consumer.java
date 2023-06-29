package com.example.challenge_backend.rabbitMQ;

import com.example.challenge_backend.response.ResponseUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class Consumer {

    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "${app-config.rabbit.queue.subscription-update}")
    public void receiveSubscriptionMessage(ResponseUser subDTO) throws JsonProcessingException {
        this.objectMapper.writeValueAsString(subDTO);
        log.info("Message received: {}", this.objectMapper.writeValueAsString(subDTO));
    }

}
