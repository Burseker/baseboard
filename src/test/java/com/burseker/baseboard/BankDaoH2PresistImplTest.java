package com.burseker.baseboard;

import com.burseker.baseboard.config.AppConfig;
import com.burseker.baseboard.dao.impl.BankDaoH2PresistImpl;
import com.burseker.baseboard.model.Account;
import com.burseker.baseboard.model.Card;
import com.burseker.baseboard.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class) // SpringRunner.class is new alias name for SpringJUnit4ClassRunner.class
@SpringBootTest
public class BankDaoH2PresistImplTest {

    @Autowired
    private BankDaoH2PresistImpl bankDaoH2Presist;

    @Test
    public void whenReadAllCustomers(){
        for (int i = 0; i < 5; i++) System.out.println("-------------------------------------------");

        List<Customer> resList = bankDaoH2Presist.getCustomers();

        for (int i = 0; i < resList.size(); i++) {
            System.out.println(resList.get(i));
        }

        assertThat(resList.size()).isEqualTo(10);
        assertThat(bankDaoH2Presist).isNotNull();

        Customer customer = bankDaoH2Presist.getCustomer(20);
        assertThat(customer).isNull();

        customer = bankDaoH2Presist.getCustomer(1);
        assertThat(customer).isNotNull();
        customer.getAccounts().forEach(System.out::println);

        for (int i = 0; i < 5; i++) System.out.println("-------------------------------------------");
    }

    @Test
    public void whenCreateSomeAccounts() {
        Customer customer;

        long custId = 1;
        for (int i = 0; i < 5; i++) System.out.println("-------------------------------------------");

        customer = bankDaoH2Presist.getCustomer(custId);
        assertThat(customer).isNotNull();
        System.out.println(customer);
        //customer.getAccounts().forEach(System.out::println);

        for(Account acc : customer.getAccounts()){
            System.out.println(acc);
            if(acc.getCard() != null)
                System.out.println(acc.getCard());
        }

        for (int i = 0; i < 5; i++) System.out.println("-------------------------------------------");

        Card card;
        card = new Card();
        card.setName("black card/debit");
        card.setUuid("12340012");
        card.setPin("9999");

        Account account1 = new Account();
        account1.setName("new born");
        account1.setBalance(new BigDecimal(100500));
        account1.setCard(card);
        card.setAccount(account1);

        bankDaoH2Presist.createAccount(custId, account1);

        customer = bankDaoH2Presist.getCustomer(custId);
        assertThat(customer).isNotNull();
        System.out.println(customer);
        //customer.getAccounts().forEach(System.out::println);

        for(Account acc : customer.getAccounts()){
            System.out.println(acc);
            if(acc.getCard() != null)
                System.out.println(acc.getCard());
        }

        for (int i = 0; i < 5; i++) System.out.println("-------------------------------------------");
    }


    @Test
    public void whenCreatingCard(){
        for (int i = 0; i < 5; i++) System.out.println("-------------------------------------------");

        System.out.println("For cust_id = 2");
        List<Account> resList = bankDaoH2Presist.getAccounts(2L);

        for (int i = 0; i < resList.size(); i++) {
            System.out.println(resList.get(i));
            if(resList.get(i).getCard() != null)
                System.out.println(resList.get(i).getCard());
        }

        Card card = new Card();
        card.setName("black card/debit");
        card.setUuid("12340001");
        card.setPin("9999");
        bankDaoH2Presist.createCard(4L, card);

        card = new Card();
        card.setName("black card/debit");
        card.setUuid("12340011");
        card.setPin("9999");
        bankDaoH2Presist.createCard(6L, card);

        card = new Card();
        card.setName("black card/debit");
        card.setUuid("12340001");
        card.setPin("9999");
        bankDaoH2Presist.createCard(5L, card);

        for (int i = 0; i < 5; i++) System.out.println("-------------------------------------------");

        System.out.println("For cust_id = 2");
        resList = bankDaoH2Presist.getAccounts(2L);

        for (int i = 0; i < resList.size(); i++) {
            System.out.println(resList.get(i));
            if(resList.get(i).getCard() != null)
                System.out.println(resList.get(i).getCard());
        }

        for (int i = 0; i < 5; i++) System.out.println("-------------------------------------------");

        bankDaoH2Presist.updateBalanceByCardUuid("12340011", new BigDecimal(120));

        System.out.println("For cust_id = 2");
        resList = bankDaoH2Presist.getAccounts(2L);

        for (int i = 0; i < resList.size(); i++) {
            System.out.println(resList.get(i));
            if(resList.get(i).getCard() != null)
                System.out.println(resList.get(i).getCard());
        }

        for (int i = 0; i < 5; i++) System.out.println("-------------------------------------------");

    }

    @Test
    public void whenAlteringCardBalance() {
        for (int i = 0; i < 5; i++) System.out.println("-------------------------------------------");

        bankDaoH2Presist.updateBalanceByCardUuid("22330000", new BigDecimal(120.3393));

        System.out.println("For cust_id = 1");
        List<Account> resList = bankDaoH2Presist.getAccounts(1L);

        for (int i = 0; i < resList.size(); i++) {
            System.out.println(resList.get(i));
            if (resList.get(i).getCard() != null)
                System.out.println(resList.get(i).getCard());
        }
        for (int i = 0; i < 5; i++) System.out.println("-------------------------------------------");
    }


    @Test
    public void whenUsingSomeFeatures(){
        for (int i = 0; i < 5; i++) System.out.println("-------------------------------------------");

        System.out.println("For cust_id = 1");
        List<Account> resList = bankDaoH2Presist.getAccounts(1L);

        for (int i = 0; i < resList.size(); i++) {
            System.out.println(resList.get(i));
        }

        for (int i = 0; i < 5; i++) System.out.println("-------------------------------------------");

        System.out.println("For cust_id = 2");
        resList = bankDaoH2Presist.getAccounts(2L);

        for (int i = 0; i < resList.size(); i++) {
            System.out.println(resList.get(i));
        }

        for (int i = 0; i < 5; i++) System.out.println("-------------------------------------------");

        System.out.println("For cust_id = 0");
        resList = bankDaoH2Presist.getAccounts(0L);

        for (int i = 0; i < resList.size(); i++) {
            System.out.println(resList.get(i));
        }

        for (int i = 0; i < 5; i++) System.out.println("-------------------------------------------");
    }

}
