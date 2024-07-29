package org.archadu.core.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    public static final String COVER_QUEUE = "cover-queue";
    public static final String COVER_EXCHANGE = "cover-exchange";



    @Bean
    public Queue coverQueue(){
        return new Queue(COVER_QUEUE, false, false, false);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public DirectExchange coverExchange(){
        return new DirectExchange(COVER_EXCHANGE, true, false);
    }

    @Bean
    public Binding bindingCoverQueue(Queue coverQueue, DirectExchange coverExchange){
        return BindingBuilder.bind(coverQueue).to(coverExchange).with(COVER_QUEUE);
    }



}
