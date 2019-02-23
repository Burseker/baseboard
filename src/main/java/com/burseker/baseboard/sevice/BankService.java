package com.burseker.baseboard.sevice;

import com.burseker.baseboard.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BankService {
    Customer getCustomer(int custId);
    List<Customer> getCustomers();
    Page<Customer> getCustomersPaginated(Pageable pageable);

    void setCustomer(Customer customer);
}
