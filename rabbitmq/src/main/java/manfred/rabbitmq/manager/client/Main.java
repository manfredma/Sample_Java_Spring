package manfred.rabbitmq.manager.client;

import com.rabbitmq.http.client.Client;

import java.net.URI;

public class Main {
    public static void main(String[] args) {
        Client client = getRabbitMQClient();
        client.getQueues().forEach(System.out::println);
    }

    private static Client getRabbitMQClient() {
        try {
            String uri = "http://guest:guest@localhost:15672/api/";
            String username = "guest";
            String password = "guest";
            URI theUri = new URI(uri);
            String userInfo = theUri.getUserInfo();
            if (userInfo != null) {
                String[] userParts = userInfo.split(":");
                if (userParts.length > 0) {
                    username = userParts[0];
                }
                if (userParts.length > 1) {
                    password = userParts[1];
                }
            }
            return new Client(uri, username, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
