package com.whs.orders.producer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Configuration class, which will use the queue name and exchange type to bind the queue to the microservice module.
 * This will also send the message content to the queue using RabbitTemplate
 */
@Component
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${javatechstack.rabbitmq.exchange}")
    private String exchange;

    @Value("${javatechstack.rabbitmq.routingkey}")
    private String routingkey;

    @Value("${javatechstack.rabbitmq.queue}")
    private String queueName;

    /**
     * Method to convert and send the message (I think that we need to change the msg to something like NotificationRequestDTO)
     * @param msg Message that you want to send
     */
    public void produceMsg(String msg){
        rabbitTemplate.convertAndSend(exchange, routingkey, msg);
        System.out.println("Send msg " + msg);
    }

    @Bean
    Queue queue(){
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind( queue).to(exchange).with(routingkey);
    }
}
