package com.zuojie.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

/**
 * 创建一个队列
 */
@Configuration
public class QueueConfig {
    @Value("${queue}")
    private String QueueName;
    @Bean
    public Queue queue(){
        return new ActiveMQQueue(QueueName);

    }
}
