package property.editor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        // create and configure beans
        ApplicationContext context = new ClassPathXmlApplicationContext("property/editor/other.xml");
        Boss boss = (Boss) context.getBean("boss");
        System.out.println(boss.getCar());
        System.out.println(boss.getHouse());
    }
}
