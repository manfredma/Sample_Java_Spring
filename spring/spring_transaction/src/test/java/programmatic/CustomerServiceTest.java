package programmatic;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CustomerServiceTest {

    ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("programmatic/jpa-beans.xml");

    }

    @Test
    public void testAddSuccessWithTransaction() {
        CustomerAddCommandWithGlobalTransaction customerAddCommandWithGlobalTransaction = new
                CustomerAddCommandWithGlobalTransaction();
        customerAddCommandWithGlobalTransaction.invokeSuccess();
    }

    @Test
    public void testAddFailWithTransaction() {
        CustomerAddCommandWithGlobalTransaction customerAddCommandWithGlobalTransaction = new
                CustomerAddCommandWithGlobalTransaction();
        customerAddCommandWithGlobalTransaction.invokeFail();

    }

    @Test
    public void testAddSuccessWithoutTransaction() {
        CustomerAddCommandWithoutGlobalTransaction customerAddCommandWithoutGlobalTransaction = new
                CustomerAddCommandWithoutGlobalTransaction();
        customerAddCommandWithoutGlobalTransaction.invokeSuccess();
    }

    @Test
    public void testAddFailWithoutTransaction() {
        CustomerAddCommandWithoutGlobalTransaction customerAddCommandWithoutGlobalTransaction = new
                CustomerAddCommandWithoutGlobalTransaction();
        customerAddCommandWithoutGlobalTransaction.invokeFail();
    }

}
