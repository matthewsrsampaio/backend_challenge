package com.example.challenge_backend.rabbitMQ;

import com.example.challenge_backend.model.Event;
import com.example.challenge_backend.model.Status;
import com.example.challenge_backend.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${app-config.rabbit.exchange.subscription-api}")
    private String subscriptionTopicExchange;

    @Value("${app-config.rabbit.routingKey.subscription-user}")
    private String subscriptionRoutingKeyUser;

    @Value("${app-config.rabbit.routingKey.subscription-status}")
    private String subscriptionRoutingKeyStatus;

    @Value("${app-config.rabbit.routingKey.subscription-event}")
    private String subscriptionRoutingKeyEvent;

    private final ObjectMapper objectMapper;

    public Producer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void produceMessageUser(User message) {
        try{
            rabbitTemplate.convertAndSend(subscriptionTopicExchange, subscriptionRoutingKeyUser, message);
            log.info("Message sent: {}", this.objectMapper.writeValueAsString(message));
        } catch (Exception e) {
            log.info("Error in produceMessageUser method", e);
        }
    }

    public void produceMessageStatus(Status message) {
        try{
            rabbitTemplate.convertAndSend(subscriptionTopicExchange, subscriptionRoutingKeyStatus, message);
            log.info("Message sent: {}", this.objectMapper.writeValueAsString(message));
        } catch (Exception e) {
            log.info("Error in produceMessageStatus method", e);
        }
    }

    public void produceMessageEvent(Event message) {
        try{
            rabbitTemplate.convertAndSend(subscriptionTopicExchange, subscriptionRoutingKeyEvent, message);
            log.info("Message sent: {}", this.objectMapper.writeValueAsString(message));
        } catch (Exception e) {
            log.info("Error in produceMessageEvent method", e);
        }
    }

}
