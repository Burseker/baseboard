package com.burseker.baseboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//public class BaseboardApplication {
public class BaseboardApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BaseboardApplication.class);
    }

    public static void main(String[] args) {
		SpringApplication.run(BaseboardApplication.class, args);
	}
}

