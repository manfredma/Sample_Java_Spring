package listener;

import org.springframework.context.ApplicationEvent;

public class RechargeEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public RechargeEvent(Object source) {
        super(source);
    }
}
