package com.atu.green_global_initiative_api.controller;

import com.atu.green_global_initiative_api.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * A global exception handler for the application.
 * <p>
 * This controller is responsible for handling exceptions across the application
 * and returning user-friendly, structured error responses to clients.
 * Specifically, it handles {@link ResourceNotFoundException}.
 * </p>
 *
 * <p><b>Features:</b></p>
 * <ul>
 *     <li>Centralized exception handling for consistent error management.</li>
 *     <li>Structured error responses with detailed information.</li>
 *     <li>Logging of exceptions for debugging and tracking purposes.</li>
 * </ul>
 */


@RestController
public class GlobalExceptionController {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionController.class);

    /**
     * Handles {@link ResourceNotFoundException} and returns a structured error response.
     *
     * <p>This method logs the exception details and constructs a JSON response
     * containing information about the error.</p>
     *
     * @param ex the {@link ResourceNotFoundException} thrown in the application
     * @return a {@link ResponseEntity} containing the error response
     * with HTTP status 404 (Not Found)
     */

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<HashMap<String, Object>> handleResourceNotFound(ResourceNotFoundException ex) {
        // Log the exception
        logger.error("Resource not found: {}", ex.getMessage());

        // Construct the error response
        HashMap<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.NOT_FOUND.value());
        errorResponse.put("error", "Resource Not Found");
        errorResponse.put("message", ex.getMessage());

        // Return the error response with HTTP status 404
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
