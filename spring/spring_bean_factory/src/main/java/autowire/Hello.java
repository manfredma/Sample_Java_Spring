package autowire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.inject.Inject;

public class Hello implements IHello {

    @Autowired
    @Qualifier("world")
    private World world;

    @Inject
    private World world2;

    @Override
    public void sayHello() {
        System.out.println("Hello World!" + world + ":" + world2);
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}