package ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Basic {
    public static void main(String[] args) {
        // create and configure beans
        ApplicationContext context = new ClassPathXmlApplicationContext("ioc/other.xml");

        // retrieve configured instance
        ExampleBean e1 = context.getBean("exampleBean", ExampleBean.class);
        ExampleBean e2 = context.getBean("exampleBean2", ExampleBean.class);
        ExampleBean e3 = context.getBean("exampleBean3", ExampleBean.class);

        System.out.println(e1);
        System.out.println(e2);
        System.out.println(e3);
    }
}
