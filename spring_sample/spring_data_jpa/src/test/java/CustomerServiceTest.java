import domain.Customer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.api.ICustomerService;

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
        customer.setId(1L);
        customer.setName("1");
        Customer persitenced = customerService.add(customer);
        System.out.println(persitenced.getId() + " - " + persitenced.getName());
    }
}
