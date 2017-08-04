package manfred.rabbitmq.topic;

import com.rabbitmq.client.*;

import java.io.IOException;

public class ReceiveLogsTopic2 {

    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel0C0 = connection.createChannel();
        Connection connection2 = factory.newConnection();
        Channel channel2C1 = connection2.createChannel();
        Channel channel1C0 = connection.createChannel();
        // 声明一个匹配模式的交换器
        channel0C0.exchangeDeclare(EXCHANGE_NAME, "topic");
        String queueName = channel0C0.queueDeclare().getQueue();
        // 路由关键字
        String[] routingKeys = new String[]{"*.*.rabbit", "lazy.#"};
        // 绑定路由关键字
        for (String bindingKey : routingKeys) {
            channel0C0.queueBind(queueName, EXCHANGE_NAME, bindingKey);
            System.out.println("ReceiveLogsTopic2 exchange:" + EXCHANGE_NAME + ", queue:" + queueName + ", " +
                    "BindRoutingKey:" + bindingKey);
        }

        System.out.println("ReceiveLogsTopic2 [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel0C0) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[]
                    body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("ReceiveLogsTopic2 [x] Received '" + envelope.getRoutingKey() + "':'" + message +
                        "'" + " envelope=" + envelope);
                try {
                    Thread.sleep(5000L);
                    channel0C0.basicAck(envelope.getDeliveryTag(), Boolean.FALSE);
//                    channel2.basicAck(envelope.getDeliveryTag(), Boolean.FALSE);
                } catch (Exception e) {
                    System.out.println("=======================");
                    System.out.println(envelope);
                    System.out.println(e);
                    System.out.println("=======================");
                }
                System.out.println("确认完成！！！");
            }
        };
        channel0C0.basicConsume(queueName, false, consumer);
    }
}