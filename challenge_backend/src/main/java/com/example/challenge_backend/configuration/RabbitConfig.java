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

    @Value("${app-config.rabbit.routingKey.subscription-update}")
    private String subscriptionRoutingKey;

    @Value("${app-config.rabbit.queue.subscription-update}")
    private String subscriptionQueue;

    @Value("${app-config.rabbit.routingKey.consumer-update}")
    private String consumerRoutingKey;

    @Value("${app-config.rabbit.queue.consumer-update}")
    private String consumerQueue;

    @Bean
    public TopicExchange subscriptionTopicExchange() {
        return new TopicExchange(subscriptionTopicExchange);
    }

    @Bean
    public Queue subscriptionQueue() {
        return new Queue(subscriptionQueue, true);
    }

    @Bean
    public Queue consumerQueue() {
        return new Queue(consumerQueue, true);
    }

    @Bean
    public Binding subscriptionTopicQueueBinding(TopicExchange topicExchange) {
        return BindingBuilder
                .bind(subscriptionQueue())
                .to(topicExchange)
                .with(subscriptionRoutingKey);
    }

    @Bean
    public Binding consumerTopicQueueBinding(TopicExchange topicExchange) {
        return BindingBuilder
                .bind(consumerQueue())
                .to(topicExchange)
                .with(consumerRoutingKey);
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
