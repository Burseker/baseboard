package com.burseker.baseboard.sevice;

import com.burseker.baseboard.model.Customer;

import java.util.List;

public interface BankService {
    Customer getCustomer(int custId);
    List<Customer> getCustomers();

    void setCustomer(Customer customer);
}
