package com.burseker.baseboard.dao;

import com.burseker.baseboard.model.Customer;

import java.util.List;

public interface BankDao {
    Customer getCustomer(int custId);
    List<Customer> getCustomers();

    void setCustomer(Customer customer);
}
