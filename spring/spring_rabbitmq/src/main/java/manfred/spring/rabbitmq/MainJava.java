package manfred.spring.rabbitmq;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

public class MainJava {
    public static void main(final String... args) throws Exception {

        ConnectionFactory cf = new CachingConnectionFactory("localhost", 5672);

        // set up the queue, exchange, binding on the broker
        RabbitAdmin admin = new RabbitAdmin(cf);
        Queue queue = new Queue("myQueue");
        admin.declareQueue(queue);
        TopicExchange exchange = new TopicExchange("myExchange-delay");
        exchange.setDelayed(Boolean.TRUE);
        admin.declareExchange(exchange);
        admin.declareBinding(BindingBuilder.bind(queue).to(exchange).with("foo.*"));

        // set up the listener and container
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(cf);
        Object listener = new Object() {
            public void handleMessage(String foo) {
                System.out.println(foo);
            }
        };
        MessageListenerAdapter adapter = new MessageListenerAdapter(listener);
        container.setMessageListener(adapter);
        container.setQueueNames("myQueue");
        container.start();

        // send something
        RabbitTemplate template = new RabbitTemplate(cf);
        template.convertAndSend("myExchange-delay", "foo.bar", "Hello, world!", new MessagePostProcessor() {

            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setDelay(5000);
                return message;
            }

        });
        Thread.sleep(10000);
        container.stop();
    }
}
