package manfred.spring.rabbitmq.manual;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.GetResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.ChannelCallback;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.DefaultMessagePropertiesConverter;
import org.springframework.amqp.rabbit.support.MessagePropertiesConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Properties;
import java.util.function.Consumer;

@Service
public class RabbitAdminServices {

    private static final Logger logger = LoggerFactory.getLogger(RabbitAdminServices.class);

    @Autowired
    AmqpAdmin rabbitAdmin;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    MessageConverter messageConverter;

    private volatile MessagePropertiesConverter messagePropertiesConverter = new DefaultMessagePropertiesConverter();

    public int getCount(String queueName) {

        Properties properties = rabbitAdmin.getQueueProperties(queueName);
        return (Integer) properties.get(RabbitAdmin.QUEUE_MESSAGE_COUNT);
    }

    public <T> void processQueue(String queueName, Integer count, Class<T> clazz, Consumer<T> consumer) {

        int reprocessCount = getCount(queueName);
        int requestCount = reprocessCount;
        if (count != null) {
            requestCount = count;
        }
        for (int i = 0; i < reprocessCount && i < requestCount; i++) {
            rabbitTemplate.execute(new ChannelCallback<T>() {

                @Override
                public T doInRabbit(Channel channel) throws Exception {
                    GetResponse response = channel.basicGet(queueName, false);
                    T result = null;
                    try {
                        MessageProperties messageProps = messagePropertiesConverter.toMessageProperties(response
                                .getProps(), response.getEnvelope(), "UTF-8");
                        if (response.getMessageCount() >= 0) {
                            messageProps.setMessageCount(response.getMessageCount());
                        }
                        Message message = new Message(response.getBody(), messageProps);
                        result = (T) messageConverter.fromMessage(message);
                        consumer.accept(result);
                        channel.basicAck(response.getEnvelope().getDeliveryTag(), false);
                    } catch (Exception e) {
                        channel.basicNack(response.getEnvelope().getDeliveryTag(), false, true);
                    }
                    return result;
                }
            });

        }
    }
}