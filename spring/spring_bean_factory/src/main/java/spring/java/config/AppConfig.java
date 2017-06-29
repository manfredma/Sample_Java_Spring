package spring.java.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

@Configuration
// @ComponentScan(basePackages = "spring.java.config")
public class AppConfig {

    @Bean(name = "myService")
    @Description("Provides a basic example of a bean")
    public MyService getBean() {
        return new MyService();
    }

    @Bean
    public TransferService transferService() {
        return new TransferServiceImpl();
    }

    @Bean(initMethod = "init")
    public Foo foo() {
        Foo foo = new Foo();
        foo.setBar(bar());
        return foo;
    }

    @Bean(destroyMethod = "cleanup")
    public Bar bar() {
        return new Bar();
    }

}