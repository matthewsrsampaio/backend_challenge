package com.example.challenge_backend.rabbitMQ;

import com.example.challenge_backend.response.ResponseEvent;
import com.example.challenge_backend.response.ResponseStatus;
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
    public void receiveUserMessage(ResponseUser responseUser) throws JsonProcessingException {
        this.objectMapper.writeValueAsString(responseUser);
        log.info("Message received: {}", this.objectMapper.writeValueAsString(responseUser));
    }

    @RabbitListener(queues = "${app-config.rabbit.queue.subscription-update}")
    public void receiveStatusMessage(ResponseStatus responseStatus) throws JsonProcessingException {
        this.objectMapper.writeValueAsString(responseStatus);
        log.info("Message received: {}", this.objectMapper.writeValueAsString(responseStatus));
    }

    @RabbitListener(queues = "${app-config.rabbit.queue.subscription-update}")
    public void receiveEventMessage(ResponseEvent responseEvent) throws JsonProcessingException {
        this.objectMapper.writeValueAsString(responseEvent);
        log.info("Message received: {}", this.objectMapper.writeValueAsString(responseEvent));
    }

}
