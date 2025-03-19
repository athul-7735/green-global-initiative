package com.atu.green_global_initiative_api.exception;

/**
 * Exception thrown when a requested resource is not found.
 * This class extends {@link RuntimeException} and is typically used
 * to signal when a resource that the application is trying to access
 * cannot be located.
 */

public class ResourceNotFoundException  extends RuntimeException {

    /**
     * Constructs a new ResourceNotFoundException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval
     *                by the {@link Throwable#getMessage()} method).
     */
        public ResourceNotFoundException(String message) {
            super(message);
        }
}
