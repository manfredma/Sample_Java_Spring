package service;

import domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CustomerRepository;
import service.api.ICustomerService;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer add(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public Customer modify(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public Customer del(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public Customer findById(Long customerId) {
        return customerRepository.findOne(customerId);
    }
}
