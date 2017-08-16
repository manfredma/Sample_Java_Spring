package listener;

import org.springframework.context.ApplicationListener;

public class EventListener implements ApplicationListener<RechargeEvent> {

    public EventListener() {
        System.out.println("======================== construct ====================");
    }

    @Override
    public void onApplicationEvent(RechargeEvent event) {
        System.out.println("event == " + event);
    }
}
