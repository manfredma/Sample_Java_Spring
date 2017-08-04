package listener;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        //1、读取配置文件实例化一个IoC容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"listener/helloworld.xml"},
                Boolean.FALSE);
        context.refresh();
        context.publishEvent(new RechargeEvent(new Object()));

        //2、从容器中获取Bean，注意此处完全“面向接口编程，而不是面向实现”
        Hello helloApi = context.getBean("hello", Hello.class);
        //3、执行业务逻辑
        helloApi.sayHello();
//        System.in.read();
    }

}
