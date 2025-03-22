package com.atu.green_global_initiative_api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class NausicaaGreenInitiativeApplicationTests {

	@Test
	void contextLoads() {
		NausicaaGreenInitiativeApplication.main(new String[]{}); // Ensures the application starts
		assertNotNull(new NausicaaGreenInitiativeApplication()); // Just to ensure class instantiation
	}

//	@Test
//	void testMainMethodLogsMessage() {
//		try (MockedStatic<SpringApplication> springAppMock = mockStatic(SpringApplication.class);
//			 MockedStatic<LoggerFactory> loggerFactoryMock = mockStatic(LoggerFactory.class)) {
//
//			Logger mockLogger = mock(Logger.class);
//			loggerFactoryMock.when(() -> LoggerFactory.getLogger(NausicaaGreenInitiativeApplication.class))
//					.thenReturn(mockLogger);
//
//			NausicaaGreenInitiativeApplication.main(new String[]{});
//
//			verify(mockLogger).info("Started Nausicaa Green Initiative Application");
//			springAppMock.verify(() -> SpringApplication.run(NausicaaGreenInitiativeApplication.class, new String[]{}));
//		}
//	}

//	@Test
//	void testMainMethodLogsMessage(CapturedOutput output) {
//		try (MockedStatic<SpringApplication> springAppMock = mockStatic(SpringApplication.class)) {
//
//			// Mock Logger
//			Logger mockLogger = mock(Logger.class);
//			NausicaaGreenInitiativeApplication.setLogger(mockLogger);
//
//			// Run main method
//			NausicaaGreenInitiativeApplication.main(new String[]{});
//
//			// Verify logger was called
//			verify(mockLogger).info("Started Nausicaa Green Initiative Application");
//			springAppMock.verify(() -> SpringApplication.run(NausicaaGreenInitiativeApplication.class, new String[]{}));
//		}
//	}

//	@Test
//	void testMainMethodLogsMessage(CapturedOutput output) {
//		NausicaaGreenInitiativeApplication.main(new String[]{});
//
//		// Assert that the log contains the expected message
//		assertTrue(output.getOut().contains("Started Nausicaa Green Initiative Application"));
//	}

}
