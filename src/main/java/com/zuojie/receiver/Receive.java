package com.zuojie.receiver;

import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;

/**
 * 功能描述：使用点对点通信 生产者模式
 * @author:zuojie
 */
public class Receive {
    private static final String USERNAME="admin";
    private static final  String PASSWORD="admin";
    private static  final String BREAKERURL="tcp://10.211.55.11:61616";
    private static  final String QUEUENAME="zuojie";

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
        Queue queue = session.createQueue(QUEUENAME);
        //创建一个消费者
        MessageConsumer consumer = session.createConsumer(queue);
        while (true) {
            TextMessage textMessage = (TextMessage) consumer.receive();
            if(textMessage!=null) {
                System.out.println("我是消费者，内容" + textMessage.getText());
            }else {
                break;
            }
        }
        //关闭session
        session.close();
        //关闭连接
        connection.close();


    }

}
