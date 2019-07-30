package com.mzd.redistimeout.utils.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "bootBaseQueue")
public class RabbitmqReceive {

    @RabbitHandler
    public void receive(String string) {
        System.out.println("收到消息：" + string);
    }

}
