package com.getir.ebooks.customer.service.impl;

import com.getir.ebooks.customer.entity.Customer;
import com.getir.ebooks.customer.exception.UniqueEmailViolationException;
import com.getir.ebooks.customer.repository.CustomerRepository;
import com.getir.ebooks.customer.service.CustomerService;
import com.getir.ebooks.order.exception.CustomerEntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        final boolean isExistEmail = customerRepository.existsByEmail(customer.getEmail());
        if(isExistEmail) {
            throw new UniqueEmailViolationException("this email has already registered.");
        }
        return customerRepository.save(customer);
    }

    @Override
    public Customer findCustomerById(Integer customerId) {
        Supplier<CustomerEntityNotFoundException> s =
                () -> new CustomerEntityNotFoundException(customerId + " customerId: not found");
        return customerRepository.findById(customerId).orElseThrow(s);
    }
}
