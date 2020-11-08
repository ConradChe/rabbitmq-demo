package topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("topics", "topic");
        String routingKey = "user.save.more";
        channel.basicPublish("topics", routingKey, null,
                ("这是topic动态路由模型，routingKey：【" + routingKey + "】").getBytes());
        RabbitMQUtils.closeConnectionAndChanel(connection, channel);
    }
}
