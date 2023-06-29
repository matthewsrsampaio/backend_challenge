package com.example.challenge_backend.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${app-config.rabbit.exchange.subscription-api}")
    private String subscriptionTopicExchange;

    @Value("${app-config.rabbit.routingKey.subscription-user}")
    private String subscriptionRoutingKeyUser;

    @Value("${app-config.rabbit.queue.subscription-user}")
    private String subscriptionQueueUser;

    @Value("${app-config.rabbit.routingKey.subscription-status}")
    private String subscriptionRoutingKeyStatus;

    @Value("${app-config.rabbit.queue.subscription-status}")
    private String subscriptionQueueStatus;

    @Value("${app-config.rabbit.routingKey.subscription-event}")
    private String subscriptionRoutingKeyEvent;

    @Value("${app-config.rabbit.queue.subscription-event}")
    private String subscriptionQueueEvent;

    @Bean
    public TopicExchange subscriptionTopicExchange() {
        return new TopicExchange(subscriptionTopicExchange);
    }

    @Bean
    public Queue subscriptionQueueUser() {
        return new Queue(subscriptionQueueUser, true);
    }

    @Bean
    public Queue subscriptionQueueStatus() {
        return new Queue(subscriptionQueueStatus, true);
    }

    @Bean
    public Queue subscriptionQueueEvent() {
        return new Queue(subscriptionQueueEvent, true);
    }

    @Bean
    public Binding subscriptionTopicQueueBindingUser(TopicExchange topicExchange) {
        return BindingBuilder
                .bind(subscriptionQueueUser())
                .to(topicExchange)
                .with(subscriptionRoutingKeyUser);
    }

    @Bean
    public Binding subscriptionTopicQueueBindingStatus(TopicExchange topicExchange) {
        return BindingBuilder
                .bind(subscriptionQueueStatus())
                .to(topicExchange)
                .with(subscriptionRoutingKeyStatus);
    }

    @Bean
    public Binding subscriptionTopicQueueBindingEvent(TopicExchange topicExchange) {
        return BindingBuilder
                .bind(subscriptionQueueEvent())
                .to(topicExchange)
                .with(subscriptionRoutingKeyEvent);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter(objectMapper());
        return converter;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }

}
