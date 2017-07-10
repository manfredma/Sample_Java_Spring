package manfred.spring.rabbitmq.manager;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitManagementTemplate;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String uri = "http://guest:guest@localhost:15672/api/";
        RabbitManagementTemplate template = new RabbitManagementTemplate(uri);
        List<Exchange> exchangeList = template.getExchanges();
        exchangeList.forEach(System.out::println);
    }
}
