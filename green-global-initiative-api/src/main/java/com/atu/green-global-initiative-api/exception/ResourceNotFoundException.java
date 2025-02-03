package com.atu.devops.exception;

public class ResourceNotFoundException  extends RuntimeException {

        public ResourceNotFoundException(String message) {
            super(message);
        }
}
