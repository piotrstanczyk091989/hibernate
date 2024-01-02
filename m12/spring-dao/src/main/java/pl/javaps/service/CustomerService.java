package pl.javaps.service;

import org.springframework.stereotype.Service;
import pl.javaps.dao.CustomerDao;
import pl.javaps.entity.Customer;
import pl.javaps.repository.CustomerRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CustomerService {

//    private final CustomerDao customerDao;
//
//    public CustomerService(CustomerDao customerDao) {
//        this.customerDao = customerDao;
//    }

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow();
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
}
