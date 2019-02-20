package com.burseker.baseboard.dao.impl;

import com.burseker.baseboard.dao.BankDao;
import com.burseker.baseboard.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

@Repository("BankDaoH2PresistImpl")
public class BankDaoH2PresistImpl implements BankDao {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public Customer getCustomer(int custId) {
        return null;
    }

    @Override
    public List<Customer> getCustomers() {
//        Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Customer.class);
//        return criteria.list();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Customer> result;
        try {
            entityManager.getTransaction().begin();
            result = entityManager.createQuery("from Customer", Customer.class).getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception ignore){
            return new ArrayList<>();
        } finally {
            entityManager.close();
        }
        return result;
    }

    @Override
    public void setCustomer(Customer customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
        } catch (Exception ignore){
            logger.warn("Not added exception:" + customer, ignore);
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }
}
