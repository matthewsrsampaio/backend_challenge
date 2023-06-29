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

    @RabbitListener(queues = "${app-config.rabbit.queue.subscription-user}")
    public void receiveSubscriptionMessageUser(ResponseUser message) throws JsonProcessingException {
        this.objectMapper.writeValueAsString(message);
        log.info("Message received: {}", this.objectMapper.writeValueAsString(message));
    }

    @RabbitListener(queues = "${app-config.rabbit.queue.subscription-status}")
    public void receiveSubscriptionMessageStatus(ResponseStatus message) throws JsonProcessingException {
        this.objectMapper.writeValueAsString(message);
        log.info("Message received: {}", this.objectMapper.writeValueAsString(message));
    }

    @RabbitListener(queues = "${app-config.rabbit.queue.subscription-event}")
    public void receiveSubscriptionMessageEvent(ResponseEvent message) throws JsonProcessingException {
        this.objectMapper.writeValueAsString(message);
        log.info("Message received: {}", this.objectMapper.writeValueAsString(message));
    }

}
