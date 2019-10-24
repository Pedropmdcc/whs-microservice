package com.whs.registry.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    /**
     * Responsible for consuming the message from the RabbitMQ message broker and then print it on console
     * @param msg message from the RabbitMQ
     */
    @RabbitListener(queues = "${javatechstack.rabbitmq.queue}")
    public void receivedMessage(String msg){
        System.out.println("Received Message: " + msg);
    }
}
