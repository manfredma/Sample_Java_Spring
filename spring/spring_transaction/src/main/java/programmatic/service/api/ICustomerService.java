package programmatic.service.api;


import programmatic.domain.Customer;

public interface ICustomerService {

    Customer add(Customer customer);

    Customer modify(Customer customer);

    Customer del(Customer customer);

    Customer findById(Long customerid);
}
