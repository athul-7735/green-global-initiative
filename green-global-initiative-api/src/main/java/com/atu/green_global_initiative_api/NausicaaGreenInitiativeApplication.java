package com.atu.green_global_initiative_api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * Main entry point for the Nausicaa Green Initiative Application.
 * This class is responsible for bootstrapping the Spring Boot application and initializing the system.
 * It also logs the start of the application.
 */

//@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
@SpringBootApplication
@ConfigurationPropertiesScan
public class NausicaaGreenInitiativeApplication {

	/**
	 * Logger to log application startup and other runtime events.
	 */

	static final Logger logger = LoggerFactory.getLogger(NausicaaGreenInitiativeApplication.class);
	/**
	 * Main method that starts the Spring Boot application.
	 * Logs the application startup and runs the application.
	 *
	 * @param args Command-line arguments passed during application startup.
	 */
	public static void main(String[] args) {
		// Log the start of the application
		logger.info("Started Nausicaa Green Initiative Application");
		// Start the Spring Boot application
		SpringApplication.run(NausicaaGreenInitiativeApplication.class, args);
	}
}
