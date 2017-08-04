package listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.event.ApplicationEventMulticaster;

import javax.inject.Inject;

public class Hello implements IHello, ApplicationEventPublisherAware {

    @Autowired
    @Qualifier("world")
    private World world;

    @Inject
    private World world2;

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void sayHello() {
        System.out.println("Hello World!" + world + ":" + world2);
        applicationEventPublisher.publishEvent(new RechargeEvent(new Object()));
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}