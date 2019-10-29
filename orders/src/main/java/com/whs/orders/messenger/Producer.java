package com.whs.orders.messenger;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * Method to convert and send the message (I think that we need to change the msg to something like NotificationRequestDTO)
     * @param msg Message that you want to send
     */
    public void produceMsg(String msg){
        rabbitTemplate.convertAndSend(msg);
        System.out.println("Send msg " + msg);
    }
}
