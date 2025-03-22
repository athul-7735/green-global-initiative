package com.atu.green_global_initiative_api.model.dao.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueryCreateRequestTest {

    /**
     * Test for the 'queryId' field.
     */
    @Test
    void testQueryId() {
        QueryCreateRequest request = new QueryCreateRequest();
        request.setQueryId(1);
        assertEquals(1, request.getQueryId(), "Query ID should be 1");
    }

    /**
     * Test for the 'name' field.
     */
    @Test
    void testName() {
        QueryCreateRequest request = new QueryCreateRequest();
        request.setName("John Doe");
        assertEquals("John Doe", request.getName(), "Name should be 'John Doe'");
    }

    /**
     * Test for the 'email' field.
     */
    @Test
    void testEmail() {
        QueryCreateRequest request = new QueryCreateRequest();
        request.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", request.getEmail(), "Email should be 'john.doe@example.com'");
    }

    /**
     * Test for the 'phone' field.
     */
    @Test
    void testPhone() {
        QueryCreateRequest request = new QueryCreateRequest();
        request.setPhone("1234567890");
        assertEquals("1234567890", request.getPhone(), "Phone number should be '1234567890'");
    }

    /**
     * Test for the 'message' field.
     */
    @Test
    void testMessage() {
        QueryCreateRequest request = new QueryCreateRequest();
        request.setMessage("This is a test message.");
        assertEquals("This is a test message.", request.getMessage(), "Message should be 'This is a test message.'");
    }
}