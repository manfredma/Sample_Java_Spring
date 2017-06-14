package programmatic;

import org.springframework.beans.factory.annotation.Autowired;
import programmatic.domain.Customer;
import programmatic.repository.CustomerRepository;
import programmatic.transaction.InvokerWithTransaction;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class CustomerAddCommandWithGlobalTransaction extends InvokerWithTransaction<Customer> {

    private Boolean success;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    protected Customer invokeInTransaction() {
        Customer customer = new Customer();
        customer.setName("CustomerAddCommandWithGlobalTransaction-" + (success ? "invokeSuccess" : "invokeFail") +
                "-1");
        customer.setActiveDateTime(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("+1")));
        customer.setActiveDateTime2(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("+1")));
        customerRepository.saveAndFlush(customer);
        if (!success) {
            throw new IllegalMonitorStateException("bad luck!");
        }

        customer = new Customer();
        customer.setName("CustomerAddCommandWithGlobalTransaction-" + (success ? "invokeSuccess" : "invokeFail") +
                "-2");
        customer.setActiveDateTime(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("+1")));
        customer.setActiveDateTime2(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("+1")));
        customerRepository.saveAndFlush(customer);
        return customer;
    }

    public Customer invokeSuccess() {
        success = Boolean.TRUE;
        return invoke();
    }

    public Customer invokeFail() {
        success = Boolean.FALSE;
        return invoke();
    }
}
