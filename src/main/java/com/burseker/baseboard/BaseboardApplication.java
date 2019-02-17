package com.burseker.baseboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BaseboardApplication {

	public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(BaseboardApplication.class);

        logger.trace("Starting SpringApplication");
//        logger.debug("A DEBUG Message");
//        logger.info("An INFO Message");
//        logger.warn("A WARN Message");
//        logger.error("An ERROR Message");

		SpringApplication.run(BaseboardApplication.class, args);
	}
}

