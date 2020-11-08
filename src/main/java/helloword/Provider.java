package helloword;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Provider {
    public static void main(String[] args) throws IOException, TimeoutException {
        /*//============构建链接======================
        ConnectionFactory factory = new ConnectionFactory();
        //设置rabbitmq主机
        factory.setHost("192.168.100.129");
        //设置端口
        factory.setPort(5672);
        //设置连接哪个虚拟主机
        factory.setVirtualHost("/ems");
        //设置访问虚拟主机的用户名密码
        factory.setUsername("ems");
        factory.setPassword("123");
        //===================================
        //获取链接对象
        Connection connection = factory.newConnection();*/

        //通过工具类获取链接
        Connection connection = RabbitMQUtils.getConnection();
        //获取链接通道
        Channel channel = connection.createChannel();
        //通道绑定消息队列
        //参数1：队列名称 如果队列不存在自动创建
        //参数2：定义队列是否持久化
        //参数3：是否独占队列
        //参数4：是否消费完成后自动删除队列
        //参数5：额外附加参数
        channel.queueDeclare("cc", true, false, false, null);

        //发布消息
        //参数1：交换机名称
        //参数2：队列名称
        //参数3：消息额外设置
        //参数4：消息具体内容
        channel.basicPublish("","cc",null,"hello rabbitmq".getBytes());

//        channel.close();
//        connection.close();
        //通过工具类关闭对象
        RabbitMQUtils.closeConnectionAndChanel(connection,channel);
    }
}
