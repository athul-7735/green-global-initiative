package com.atu.green_global_initiative_api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * Main application class for the Nausicaa Green Initiative API.
 *
 * <p>This is the entry point for the Spring Boot application. It initializes the application
 * context and starts the embedded server. Additionally, it scans for configuration properties
 * and logs a startup message.</p>
 */

//@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
@SpringBootApplication
@ConfigurationPropertiesScan
public class NausicaaGreenInitiativeApplication {
	/**
	 * Logger for logging application events.
	 */
	static Logger logger = LoggerFactory.getLogger(NausicaaGreenInitiativeApplication.class);

	/**
	 * Setter for logger (used in testing).
	 */
	public static void setLogger(Logger testLogger) {
		logger = testLogger;
	}

	/**
	 * Main method to launch the Spring Boot application.
	 *
	 * <p>This method logs a startup message and delegates to {@link SpringApplication#run(Class, String[])}
	 * to bootstrap the application.</p>
	 *
	 * @param args command-line arguments passed to the application.
	 */
	public static void main(String[] args) {
		logger.info("Started Nausicaa Green Initiative Application");
		SpringApplication.run(NausicaaGreenInitiativeApplication.class, args);
	}
}
