package programmatic;

import org.springframework.beans.factory.annotation.Autowired;
import programmatic.domain.Customer;
import programmatic.repository.CustomerRepository;
import programmatic.support.ApplicationContextHelper;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class CustomerAddCommandWithoutGlobalTransaction {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerAddCommandWithoutGlobalTransaction() {
        ApplicationContextHelper.autowiredBeanPropertyValues(this);
    }

    public Customer invokeSuccess() {
        Customer customer = new Customer();
        customer.setName("CustomerAddCommandWithoutGlobalTransaction-invokeSuccess-1");
        customer.setActiveDateTime(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("+1")));
        customer.setActiveDateTime2(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("+1")));
        customerRepository.saveAndFlush(customer);
        customer = new Customer();
        customer.setName("CustomerAddCommandWithoutGlobalTransaction-invokeSuccess-2");
        customer.setActiveDateTime(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("+1")));
        customer.setActiveDateTime2(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("+1")));
        customerRepository.saveAndFlush(customer);
        return customer;
    }

    public Customer invokeFail() {
        Customer customer = new Customer();
        customer.setName("CustomerAddCommandWithoutGlobalTransaction-invokeFail-1");
        customer.setActiveDateTime(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("+1")));
        customer.setActiveDateTime2(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("+1")));
        customerRepository.saveAndFlush(customer);
        if (true) {
            throw new IllegalMonitorStateException("fail");
        }
        customer = new Customer();
        customer.setName("CustomerAddCommandWithoutGlobalTransaction-invokeFail-2");
        customer.setActiveDateTime(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("+1")));
        customer.setActiveDateTime2(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("+1")));
        customerRepository.saveAndFlush(customer);
        return customer;
    }

}
