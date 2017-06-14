package service.api;

import domain.Customer;

public interface ICustomerService {

    Customer add(Customer customer);

    Customer modify(Customer customer);

    Customer del(Customer customer);

    Customer findById(Long customerid);
}
