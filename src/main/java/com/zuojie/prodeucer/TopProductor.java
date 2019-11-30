package com.zuojie.prodeucer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TopProductor {
    private static final String USERNAME="admin";
    private static final  String PASSWORD="admin";
    private static  final String BREAKERURL="tcp://10.211.55.11:61616";
    private static  final String TOPICNAME="mytop";

    public static void main(String[] args) throws JMSException {
        start();
    }
    public static void start() throws JMSException {
        //获取activeMQ 会话工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD,BREAKERURL);
        //创建activeMql连接
        Connection connection=  activeMQConnectionFactory.createConnection();
        //开启activeMQ连接
        connection.start();
        //jms设置消息的可靠性，自动签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建队列
       // Queue queue = session.createQueue(QUEUENAME);
        //创建一个topic
        Topic topic = session.createTopic(TOPICNAME);
        //创建一个生产者
        MessageProducer producer = session.createProducer(topic);
        for(int i=0;i<5;i++) {
            //设置存放消息队列内容
            TextMessage textMessage = session.createTextMessage("小凡");
            //发送消息
            producer.send(textMessage);
        }

        System.out.println("消息队列存放成功");
        //关闭连接
        connection.close();




    }
}
