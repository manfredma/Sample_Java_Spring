package hello.provider;


import hello.api.DemoService;

public class DemoServiceImpl implements DemoService {

    public void sayHello(String name) {
        System.out.println("invoked me, name is " + name);
        int sleepTime = (int) (Math.random() * 10.0) * 1000;
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("invoked me, name is " + name + ", sleep time is " + sleepTime);
        // return Collections.singletonList("Hello " + name);
    }

}
