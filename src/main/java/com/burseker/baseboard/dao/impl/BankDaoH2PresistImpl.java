package com.burseker.baseboard.dao.impl;

import com.burseker.baseboard.dao.BankDao;
import com.burseker.baseboard.model.Customer;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository("BankDaoH2PresistImpl")
public class BankDaoH2PresistImpl implements BankDao {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public Customer getCustomer(int custId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Customer customer = null;

        try{
            entityManager.getTransaction().begin();
            customer = entityManager.find(Customer.class, (long)custId);
            entityManager.getTransaction().commit();
        } catch (Exception ignore){
            logger.warn("Cant get customers", ignore);
        } finally {
            entityManager.close();
        }

        return customer;
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
            logger.warn("Cant get list", ignore);
            return new ArrayList<>();
        } finally {
            entityManager.close();
        }
        return result;
    }


    /**
     *
     * @param pageable
     * @return Page<Customer> container, that stores data and info about paging type
     */
    @Override
    public Page<Customer> getCustomersPage(Pageable pageable) {

        logger.trace("paging query from DB");
        int pageSize = pageable.getPageSize();
        int pageCurrNum = pageable.getPageNumber();

        int currPos = pageSize * pageCurrNum;

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Customer> pageCustomers = new ArrayList<>();
        long totalCustomerListSize = 0;

        try {
            entityManager.getTransaction().begin();
            BigInteger res = (BigInteger) entityManager.createNativeQuery("SELECT COUNT(f.id) FROM Customers f").getSingleResult();
            totalCustomerListSize = res.longValue();
            logger.trace("totalCustomerListSize obtained: " + totalCustomerListSize);
            if(totalCustomerListSize > currPos){
                Query query = entityManager.createQuery(QueryUtils.applySorting("FROM Customer", pageable.getSort()));
                query.setFirstResult(currPos);
                query.setMaxResults(pageSize);
                pageCustomers = query.getResultList();
            }
            entityManager.getTransaction().commit();
        } catch (Exception ignore){
            logger.warn("Can't get list", ignore);
            pageCustomers = new ArrayList<>();
        } finally {
            entityManager.close();
        }

        return new PageImpl<Customer>(pageCustomers, PageRequest.of(pageCurrNum, pageSize), totalCustomerListSize);

        /**
         * One man imply sorting this way, but think it's not good practic
         *
         *
             StringBuilder strBuilder = "SELECT p FROM projects";
             Iterator<Order> orderIterator = sort.iterator();
             Order order = orderIterator.next();
             strBuilder.append(" Order By ").append(order.getProperty()).append(" ")
             .append(order.getDirection().name());
             entityManager.createQuery(strBuilder.toString());
         */
    }



    @Override
    public void setCustomer(Customer customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        if(customer.getCust_id() == 0) {
            try {
                entityManager.getTransaction().begin();
                entityManager.persist(customer);
                entityManager.getTransaction().commit();
            } catch (Exception ignore) {
                logger.warn("Not added exception:" + customer);
                entityManager.getTransaction().rollback();
            } finally {
                entityManager.close();
            }
        } else {
            try {
                entityManager.getTransaction().begin();
                entityManager.merge(customer);
                entityManager.getTransaction().commit();
            } catch (Exception ignore) {
                logger.warn("Not updated exception:" + customer);
                entityManager.getTransaction().rollback();
            } finally {
                entityManager.close();
            }
        }

    }
}
