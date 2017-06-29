package hello.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider3 {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new
                String[]{"hello/provider3/provider.xml"});
        context.start();
        System.out.println("请按任意键退出");
        System.in.read(); // 按任意键退出
    }

}