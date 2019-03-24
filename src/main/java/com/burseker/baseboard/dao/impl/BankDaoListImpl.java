package com.burseker.baseboard.dao.impl;

import com.burseker.baseboard.dao.BankDao;
import com.burseker.baseboard.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
//@Component("BankDaoListImpl")
public class BankDaoListImpl implements BankDao{

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private List<Customer> entityList;
    private AtomicInteger currId = new AtomicInteger(0);

    public BankDaoListImpl() {
        entityList = new ArrayList<>();
    }

    @Override
    public Customer getCustomer(long custId) {
        Customer res = null;
        for(Customer c : entityList){
            if(c.getCust_id() == custId)
                res = c;
        }
        return res;
    }

    @Override
    public List<Customer> getCustomers() {
        return entityList;
    }

    @Override
    public Page<Customer> getCustomersPage(Pageable pageable) {
        logger.trace("paging query from BankDaoListImpl");
        return null;
    }

    @Override
    public void setCustomer(Customer customer) {
        Customer nCust = new Customer();
        //nCust.setCust_id(currId.getAndIncrement());
        nCust.setfName(customer.getfName());
        nCust.setlName(customer.getlName());
        nCust.setPassportId(customer.getPassportId());
        entityList.add(nCust);
    }
}
