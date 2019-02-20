package com.burseker.baseboard.sevice.impl;

import com.burseker.baseboard.dao.BankDao;
import com.burseker.baseboard.model.Customer;
import com.burseker.baseboard.sevice.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

//@Service
public class BankServiceImpl implements BankService {

    //if you want to use this annotation comment
    //constructor definition of this fild
//    @Autowired
//    @Qualifier("BankDaoListImpl")
//    @Resource(name = "BankDaoListImpl")
    private BankDao bankDao;

    public BankServiceImpl(BankDao bankDao) {
        this.bankDao = bankDao;
    }

    @Override
    public Customer getCustomer(int custId) {
        return bankDao.getCustomer(custId);
    }

    @Override
    public List<Customer> getCustomers() {
        return bankDao.getCustomers();
    }

    @Override
    public void setCustomer(Customer customer) {
        bankDao.setCustomer(customer);
    }
}
