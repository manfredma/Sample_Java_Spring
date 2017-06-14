import domain.Customer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.api.ICustomerService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class CustomerServiceTest {

    ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("jpa-beans.xml");

    }

    @Test
    public void testAdd() {
        ICustomerService customerService = applicationContext.getBean(ICustomerService.class);
        Customer customer = new Customer();
        customer.setName("1");
        customer.setActiveDateTime(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("+1")));
        customer.setActiveDateTime2(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("+1")));
        Customer persistenceCustomer = customerService.add(customer);
        System.out.println(persistenceCustomer.getId() + " - " + persistenceCustomer.getName());
    }

    @Test
    public void testFind() {
        ICustomerService customerService = applicationContext.getBean(ICustomerService.class);
        Customer persistenceCustomer = customerService.findById(16L);
        System.out.println(persistenceCustomer.getId() + " - " + persistenceCustomer.getName() + " - " + persistenceCustomer.getCreateInstant());
    }
}