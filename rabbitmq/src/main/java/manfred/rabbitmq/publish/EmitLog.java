package manfred.rabbitmq.publish;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class EmitLog {

    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] argv) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        // 分发消息
        for (int i = 0; i < 5; i++) {
            String message = "Hello World! " + i;
            AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder().messageId("2222");
            channel.basicPublish(EXCHANGE_NAME, "", builder.build(), message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }
        channel.close();
        connection.close();
    }
}