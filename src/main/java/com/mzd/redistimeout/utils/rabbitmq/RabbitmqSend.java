package com.mzd.redistimeout.utils.rabbitmq;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;




@Component
public class RabbitmqSend {


    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private RabbitTemplate rabbitTemplateDelay;

    //发送基本消息
    public void sendMessage(String queue, String value) {
        System.out.println("向" + queue + "队列发送消息：" + value);
        rabbitTemplate.convertAndSend(queue, value);
    }

    //发送延迟队列
    public void sendMessageDelay(String key, Object msg, long timeout) {
        rabbitTemplateDelay.convertAndSend(key, msg, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                System.out.println("发送延迟消息：" + msg);
                message.getMessageProperties().setDelay(Integer.valueOf(timeout + ""));
                return message;
            }
        });
    }
}
