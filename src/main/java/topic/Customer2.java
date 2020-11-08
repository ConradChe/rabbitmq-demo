package topic;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

public class Customer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("topics", "topic");
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue,"topics","user.#");
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2：" + new String(body));
            }
        });
    }
}
