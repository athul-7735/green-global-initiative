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
 * Global exception handler for the application.
 *
 * <p>This controller provides centralized exception handling for specific exceptions such as
 * {@link ResourceNotFoundException}. It ensures consistent error responses across the API.</p>
 */
@RestController
public class GlobalExceptionController {
    /**
     * Logger for logging exception details.
     */
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionController.class);
    /**
     * Handles {@link ResourceNotFoundException} thrown by the application.
     *
     * <p>This method logs the error and returns a structured error response containing:
     * <ul>
     *     <li>Timestamp of the error occurrence</li>
     *     <li>HTTP status code</li>
     *     <li>Error type</li>
     *     <li>Detailed error message</li>
     * </ul>
     *
     * @param ex the {@link ResourceNotFoundException} to handle.
     * @return a {@link ResponseEntity} containing the error response with HTTP status 404 (NOT FOUND).
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<HashMap<String, Object>> handleResourceNotFound(ResourceNotFoundException ex) {
        logger.error("Resource not found: {}", ex.getMessage());
        HashMap<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.NOT_FOUND.value());
        errorResponse.put("error", "Resource Not Found");
        errorResponse.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
