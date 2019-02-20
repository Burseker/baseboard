package com.burseker.baseboard.dao.impl;

import com.burseker.baseboard.dao.BankDao;
import com.burseker.baseboard.model.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("BankDaoH2PresistImpl")
public class BankDaoH2PresistImpl implements BankDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Customer getCustomer(int custId) {
        return null;
    }

    @Override
    public List<Customer> getCustomers() {
        return null;
    }

    @Override
    public void setCustomer(Customer customer) {

    }
}
