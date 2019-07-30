package com.mzd.redistimeout.utils.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "bootDelayQueue")
public class RabbitmqDelayReceive {

    @RabbitHandler
    public void receive(String string) {
        System.out.println("收到延迟消息：" + string);
    }

}
