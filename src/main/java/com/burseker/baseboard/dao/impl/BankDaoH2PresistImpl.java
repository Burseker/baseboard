package com.burseker.baseboard.dao.impl;

import com.burseker.baseboard.dao.BankDao;
import com.burseker.baseboard.model.*;
import org.hibernate.Hibernate;
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

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository("BankDaoH2PresistImpl")
public class BankDaoH2PresistImpl implements BankDao {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    /**
     * get customer with all linked classes by customer id
     * @param custId
     * @return
     */
    @Override
    public Customer getCustomer(long custId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Customer customer = null;
        try{
            entityManager.getTransaction().begin();
            customer = entityManager.find(Customer.class, custId);
            if(customer != null){
                Hibernate.initialize(customer.getAccounts());
            }
            entityManager.getTransaction().commit();
        } catch (Exception ignore){
            logger.warn("Cant get customers", ignore);
        } finally {
            entityManager.close();
        }
        return customer;
    }


    /**
     * get all customers
     * @return
     */
    @Override
    public List<Customer> getCustomers() {
//        Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Customer.class);
//        return criteria.list();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Customer> result;
        try {
            entityManager.getTransaction().begin();
            result = entityManager.createQuery("from Customer", Customer.class).getResultList();
//            result = entityManager.createNativeQuery("SELECT * FROM CUSTOMERS", Customer.class).getResultList();
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
     * get customers with paginating
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


    /**
     * create new customer if id=0
     * else updete customer with given id
     * if id!=0 and base have no row with this id, then nothing happend
     * @param customer
     */
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


    /**
     * get all accounts owning by customer with custId
     * think it's unnecessary, cause you can get customer with all accounts by getCustomer(int custId)
     * @param custId
     * @return
     */
    public List<Account> getAccounts(long custId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Account> accounts = new ArrayList<>();

        try{
            entityManager.getTransaction().begin();

            accounts = entityManager.createQuery("from Account where CUST_ID = :val", Account.class)
                    .setParameter("val", custId )
                    .getResultList();

            entityManager.getTransaction().commit();
        } catch (Exception ignore){
            logger.warn("Cant get accounts", ignore);
        } finally {
            entityManager.close();
        }

        return accounts;
    }


    /**
     * you can add account with bounded card, so it will not need to add card with different operation
     * @param custId
     * @param account
     */
    public Account createAccount(long custId, Account account){
        Customer customer;

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();

            customer = entityManager.find(Customer.class, custId);

            if( customer != null ){
                customer.getAccounts().add(account);
                account.setCustomer(customer);
                entityManager.persist(account);
            }
            entityManager.getTransaction().commit();
        } catch (Exception ignore){
            logger.warn("Creating account falure", ignore);
            return null;
        } finally {
            entityManager.close();
        }
        return account;
    }


    /**
     *
     * @param accId
     * @param payment
     * @return
     */
    public Payment createPayment(long accId, Payment payment){
        Account account;

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            account = entityManager.find(Account.class, accId);

            if(account != null) {
                payment.setAccount(account);
                entityManager.persist(payment);
            } else {
                logger.warn("No account payment will be attach");
            }
            entityManager.getTransaction().commit();
        } catch (Exception ignore){
            logger.warn("Creating payment falure", ignore);
            return null;
        } finally {
            entityManager.close();
        }
        return payment;
    }


    /**
     *
     * @param accId
     * @param card
     * @return
     */
    public Card createCard(long accId, Card card){
        Account account;

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            account = entityManager.find(Account.class, accId);

            if(account != null && account.getCard() == null) {
                card.setAccount(account);
                entityManager.persist(card);
            } else {
                logger.warn("Unable to add card");
                card = null;
            }
            entityManager.getTransaction().commit();
        } catch (Exception ignore){
            logger.warn("Creating card falure", ignore);
            return null;
        } finally {
            entityManager.close();
        }

        return card;
    }


    /**
     *
     * @param opRecord
     * @return
     */
    public boolean createOpRecord(OpRecord opRecord){

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(opRecord);
            entityManager.getTransaction().commit();
        } catch (Exception ignore){
            logger.warn("Creating operation records falure", ignore);
            return false;
        } finally {
            entityManager.close();
        }

        return true;
    }


    /**
     *
     * @return
     */
    public List<Payment> getPayments(){
        List<Payment> payments = null;

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            payments = entityManager.createQuery("from Payment", Payment.class).getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception ignore){
            logger.warn("Cant get list", ignore);
            return new ArrayList<>();
        } finally {
            entityManager.close();
        }
        return payments;
    }


    /**
     *
     * @param cardUuid
     * @return
     */
    public Account getAccountByCardUuid(String cardUuid){
        Account account = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Card cardPresist = entityManager.createQuery("from Card card where card.uuid = :value", Card.class)
                    .setParameter("value", cardUuid)
                    .getSingleResult();

            if(cardPresist != null) {
                account = cardPresist.getAccount();
            }

            entityManager.getTransaction().commit();
        } catch (Exception ignore){
            logger.warn("GetAccount falure", ignore);
        } finally {
            entityManager.close();
        }

        return account;
    }


    /**
     *
     * @param cardUuid
     * @param diffBalance
     */
    public void updateBalanceByCardUuid(String cardUuid, BigDecimal diffBalance){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Card cardPresist = entityManager.createQuery("from Card card where card.uuid = :value", Card.class)
                    .setParameter("value", cardUuid)
                    .getSingleResult();

            if(cardPresist != null) {
                cardPresist.getAccount().setBalance(cardPresist.getAccount().getBalance().add(diffBalance));
            }

            entityManager.getTransaction().commit();
        } catch (Exception ignore){
            logger.warn("Update balance falure", ignore);
        } finally {
            entityManager.close();
        }
    }

}
