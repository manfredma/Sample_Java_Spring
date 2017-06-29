package annotation.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("annotation/config/beans.xml");
        String[] beanNames = context.getBeanNamesForType(TestBean.class);
        for (String beanName : beanNames) {
            System.out.println(context.getBean(beanName));
        }
    }
}
