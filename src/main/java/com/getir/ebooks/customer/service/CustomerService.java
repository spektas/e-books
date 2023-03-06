package com.getir.ebooks.customer.service;

import com.getir.ebooks.customer.entity.Customer;

public interface CustomerService {
    Customer saveCustomer(Customer customer);

    Customer findCustomerById(Integer customerId);
}
