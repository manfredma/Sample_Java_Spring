package hello.consumer;

import hello.api.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Consumer {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new
                String[]{"hello/consumer/consumer.xml"});
        context.start();
        Long begin = System.currentTimeMillis();
        DemoService demoService = (DemoService) context.getBean("demoService"); // 获取远程服务代理
        demoService.sayHello("world"); // 执行远程方法
        System.out.println("Cost: " + (System.currentTimeMillis() - begin));
        //        List<String> res = demoService.sayHello("world"); // 执行远程方法
        //        System.out.println(res); // 显示调用结果
    }

}