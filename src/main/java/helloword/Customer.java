package helloword;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Customer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建链接工厂
        /*ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.100.129");
        factory.setPort(5672);
        factory.setVirtualHost("/ems");
        factory.setUsername("ems");
        factory.setPassword("123");

        Connection connection = factory.newConnection();*/
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("cc", true, false, false, null);

        //消费消息
        // 参数1：消费哪个队列的消息 队列名称
        // 参数2：开始消费的确认机制
        // 参数3：消费时的回调机制
        channel.basicConsume("cc", true, new DefaultConsumer(channel) {
            //最后一个参数：消息队列中取出的消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("new String(body) = "+new String(body));
            }
        });
//        channel.close();
//        connection.close();
    }
}
