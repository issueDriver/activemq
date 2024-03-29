package com.zuojie;

import com.alibaba.fastjson.JSON;
import com.zuojie.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;
import javax.jms.Queue;
import java.util.UUID;

@Component
@EnableScheduling
public class Producer {
    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    private int age=18;

    @Scheduled(fixedDelay = 5000)
    public void send(){
        age++;
        UserEntity userEntity = new UserEntity(System.currentTimeMillis(), UUID.randomUUID().toString(), age);
        String json = JSON.toJSONString(userEntity);
        System.out.println("json:"+json);
        jmsMessagingTemplate.convertAndSend(queue,json);
    }

}
