package com.atu.devops;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
@SpringBootApplication
public class NausicaaGreenInitiativeApplication {

	static final Logger logger = LoggerFactory.getLogger(NausicaaGreenInitiativeApplication.class);
	public static void main(String[] args) {

		logger.info("Started Nausicaa Green Initiative Application");
		SpringApplication.run(NausicaaGreenInitiativeApplication.class, args);
	}
}
