package direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        //通过通道声明交换机  参数1：交换机名称  参数2：direct  路由模式
        channel.exchangeDeclare("logs_direct", "direct");
        String routingKey = "error";
        channel.basicPublish("logs_direct", routingKey, null,
                ("这是direct模型发布的基于route key：【" + routingKey + "】发送的消息").getBytes());
        RabbitMQUtils.closeConnectionAndChanel(connection,channel);
    }
}
