package direct;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

public class Customer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, "logs_direct", "info");
        channel.queueBind(queueName, "logs_direct", "error");
        channel.queueBind(queueName, "logs_direct", "warning");
        channel.basicConsume(queueName, true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2："+new String(body));
            }
        });
    }
}
