package com.whs.warehouse.messenger;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @RabbitListener(queues = "${javatechstack.rabbitmq.queue}")
    public void receivedMessage(String msg){
        System.out.println("Received Message: " + msg);
    }
}
