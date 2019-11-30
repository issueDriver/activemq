package com.zuojie.receive;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


import com.zuojie.entity.UserEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @JmsListener(destination = "${queue}")
    public void receive(String msg){
        System.out.println(msg);
        UserEntity userEntity = JSON.parseObject(msg, UserEntity.class);
        System.out.println(userEntity.getName() + "---" + userEntity.getId());
    }
}
