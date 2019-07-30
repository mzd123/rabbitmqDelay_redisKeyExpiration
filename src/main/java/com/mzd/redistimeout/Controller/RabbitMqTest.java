package com.mzd.redistimeout.Controller;


import com.mzd.redistimeout.utils.rabbitmq.RabbitmqSend;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
public class RabbitMqTest {


    @Resource
    private RabbitmqSend rabbitmqSend;

    //发送消息--最基本的
    @RequestMapping("/rabbitmq")
    public String rabbitmq() {
        rabbitmqSend.sendMessage("bootBaseQueue", "123");
        return "success";
    }


    //延迟队列
    @RequestMapping("/rabbitmqdelay")
    public String rabbitmqdelay() {
        rabbitmqSend.sendMessageDelay("bootDelayQueue", "abc", 30000);
        return "success";
    }


}
