package workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("work", true, false, false, null);
        for (int i = 1; i <= 20; i++) {
            channel.basicPublish("","work",null,("hello work queue-"+i).getBytes());
        }

        RabbitMQUtils.closeConnectionAndChanel(connection, channel);
    }
}
