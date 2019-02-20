package com.burseker.baseboard.config;

import com.burseker.baseboard.dao.BankDao;
import com.burseker.baseboard.dao.impl.BankDaoListImpl;
import com.burseker.baseboard.sevice.BankService;
import com.burseker.baseboard.sevice.impl.BankServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
public class AppConfig {

    @Bean("BankDaoListImpl")
    public BankDao beanBankDaoListImpl(){
        return new BankDaoListImpl();
    }

    @Bean
    public BankService beanBankService(@Qualifier("BankDaoH2PresistImpl") final BankDao bankDao){
        return new BankServiceImpl(bankDao);
    }

}
