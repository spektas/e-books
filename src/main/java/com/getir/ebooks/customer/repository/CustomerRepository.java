package com.getir.ebooks.customer.repository;

import com.getir.ebooks.customer.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    boolean existsByEmail(String email);
}
