package com.burseker.baseboard.sevice.impl;

import com.burseker.baseboard.dao.BankDao;
import com.burseker.baseboard.model.Customer;
import com.burseker.baseboard.sevice.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
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
    public Page<Customer> getCustomersPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Customer> allCustomers = getCustomers();
        List<Customer> pageCustomers;

        if(allCustomers.size() < startItem ){
            pageCustomers = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, allCustomers.size());
            pageCustomers = allCustomers.subList(startItem, toIndex);
        }

        Page<Customer> res = new PageImpl<Customer>(pageCustomers, PageRequest.of(currentPage, pageSize), allCustomers.size());
        return res;
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
