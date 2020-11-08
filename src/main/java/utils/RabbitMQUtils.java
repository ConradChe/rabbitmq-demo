package utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQUtils {

    private static ConnectionFactory factory;
    static {
        factory = new ConnectionFactory();
        //设置rabbitmq主机
        factory.setHost("192.168.100.129");
        //设置端口
        factory.setPort(5672);
        //设置连接哪个虚拟主机
        factory.setVirtualHost("/ems");
        //设置访问虚拟主机的用户名密码
        factory.setUsername("ems");
        factory.setPassword("123");
    }

    //定义提供链接对象的方法
    public static Connection getConnection(){

        //===================================
        //获取链接对象
        Connection connection = null;
        try {
            connection = factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return connection;
    }

    //关闭通道和链接
    public static void closeConnectionAndChanel(Connection conn, Channel channel) {
        try {
            if(channel != null) channel.close();
            if(conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
