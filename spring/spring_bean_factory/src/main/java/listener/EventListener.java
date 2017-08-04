package listener;

import org.springframework.context.ApplicationListener;

public class EventListener implements ApplicationListener<RechargeEvent> {

    @Override
    public void onApplicationEvent(RechargeEvent event) {
        System.out.println("event == " + event);
    }
}
