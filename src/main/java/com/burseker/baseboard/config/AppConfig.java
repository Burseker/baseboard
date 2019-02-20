package com.burseker.baseboard.config;

import org.h2.server.web.WebServlet;
import com.burseker.baseboard.dao.BankDao;
import com.burseker.baseboard.dao.impl.BankDaoListImpl;
import com.burseker.baseboard.sevice.BankService;
import com.burseker.baseboard.sevice.impl.BankServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
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

    @Bean
    ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }
}
