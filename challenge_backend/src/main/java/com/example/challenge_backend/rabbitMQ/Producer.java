package com.example.challenge_backend.rabbitMQ;

import com.example.challenge_backend.model.User;
import com.example.challenge_backend.request.RequestAll;
import com.example.challenge_backend.response.ResponseAll;
import com.example.challenge_backend.response.ResponseUser;
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

    private final ObjectMapper objectMapper;

    public Producer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Value("${app-config.rabbit.exchange.subscription-api}")
    private String subscriptionTopicExchange;

    @Value("${app-config.rabbit.routingKey.subscription-update}")
    private String subscriptionRoutingKey;

    @Value("${app-config.rabbit.routingKey.consumer-update}")
    private String consumerRoutingKey;

    public void produceMessageUser(User message) {
        try{
            rabbitTemplate.convertAndSend(subscriptionTopicExchange, subscriptionRoutingKey, message);
            rabbitTemplate.convertAndSend(subscriptionTopicExchange, consumerRoutingKey, message);
            log.info("Message sent: {}", this.objectMapper.writeValueAsString(message));
        } catch (Exception e) {
            log.info("Error in produceMessage method", e);
        }
    }

}
