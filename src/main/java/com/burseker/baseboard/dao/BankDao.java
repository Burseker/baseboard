package com.burseker.baseboard.dao;

import com.burseker.baseboard.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BankDao {
    Customer getCustomer(long custId);
    List<Customer> getCustomers();
    Page<Customer> getCustomersPage(Pageable pageable);

    void setCustomer(Customer customer);
}
