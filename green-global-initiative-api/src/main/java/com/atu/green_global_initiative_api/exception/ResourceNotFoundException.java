package com.atu.green_global_initiative_api.exception;

/**
 * Exception thrown when a requested resource is not found.
 * This extends the RuntimeException and is used to signal that a resource
 * does not exist or is unavailable.
 */
public class ResourceNotFoundException  extends RuntimeException {

    /**
     * Constructs a new ResourceNotFoundException with the specified detail message.
     *
     * @param message the detail message which provides more information about the exception
     */
        public ResourceNotFoundException(String message) {
            super(message);
        }
}
