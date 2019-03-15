package com.burseker.baseboard;

import com.burseker.baseboard.config.AppConfig;
import com.burseker.baseboard.dao.impl.BankDaoH2PresistImpl;
import com.burseker.baseboard.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

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

        customer = bankDaoH2Presist.getCustomer(10);
        assertThat(customer).isNotNull();

        for (int i = 0; i < 5; i++) System.out.println("-------------------------------------------");
    }


}
