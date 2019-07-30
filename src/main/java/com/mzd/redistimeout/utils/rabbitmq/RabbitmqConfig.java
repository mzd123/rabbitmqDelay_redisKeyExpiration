package com.mzd.redistimeout.utils.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitmqConfig {


    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.username}")
    private String username;


    @Value("${spring.rabbitmq.password}")
    private String password;


    @Value("${spring.rabbitmq.port}")
    private int port;


    @Value("${spring.rabbitmq.virtual-host}")
    private String vhost;


    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(vhost);
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }


    //申明一个基本的队列
    @Bean
    public Queue bootBaseQueue() {
        return new Queue("bootBaseQueue");
    }


    //延迟队列
    @Bean
    public Queue bootDelayQueue() {
        return new Queue("bootDelayQueue");
    }


    @Bean
    public FanoutExchange bootDelayExchange() {
        FanoutExchange fanoutExchange = new FanoutExchange("bootDelayExchange");
        fanoutExchange.setDelayed(true);
        return fanoutExchange;
    }

    @Bean
    public Binding bindingPaymentExchange(Queue bootDelayQueue, FanoutExchange bootDelayExchange) {
        return BindingBuilder.bind(bootDelayQueue).to(bootDelayExchange);
    }


    @Bean
    public RabbitTemplate rabbitTemplateDelay(Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setExchange("bootDelayExchange");
        template.setMessageConverter(jackson2JsonMessageConverter);
        return template;
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
