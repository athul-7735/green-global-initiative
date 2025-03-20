package com.atu.green_global_initiative_api.model.dao.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationCreateRequestTest {


    /**
     * Test for the 'applicationId' field.
     */
    @Test
    void testApplicationId() {
        ApplicationCreateRequest request = new ApplicationCreateRequest();
        request.setApplicationId(101);
        assertEquals(101, request.getApplicationId(), "Application ID should be 101");
    }

    /**
     * Test for the 'userId' field.
     */
    @Test
    void testUserId() {
        ApplicationCreateRequest request = new ApplicationCreateRequest();
        request.setUserId(202);
        assertEquals(202, request.getUserId(), "User ID should be 202");
    }

    /**
     * Test for the 'organizationName' field.
     */
    @Test
    void testOrganizationName() {
        ApplicationCreateRequest request = new ApplicationCreateRequest();
        request.setOrganizationName("GreenTech Inc.");
        assertEquals("GreenTech Inc.", request.getOrganizationName(), "Organization Name should be 'GreenTech Inc.'");
    }

    /**
     * Test for the 'grantId' field.
     */
    @Test
    void testGrantId() {
        ApplicationCreateRequest request = new ApplicationCreateRequest();
        request.setGrantId(303);
        assertEquals(303, request.getGrantId(), "Grant ID should be 303");
    }

    /**
     * Test for the 'applicationStatus' field.
     */
    @Test
    void testApplicationStatus() {
        ApplicationCreateRequest request = new ApplicationCreateRequest();
        request.setApplicationStatus("Pending");
        assertEquals("Pending", request.getApplicationStatus(), "Application Status should be 'Pending'");
    }

    /**
     * Test for the 'approvalDate' field.
     */
    @Test
    void testApprovalDate() {
        ApplicationCreateRequest request = new ApplicationCreateRequest();
        request.setApprovalDate("2025-12-31");
        assertEquals("2025-12-31", request.getApprovalDate(), "Approval Date should be '2025-12-31'");
    }

    /**
     * Test for the 'requestedAmount' field.
     */
    @Test
    void testRequestedAmount() {
        ApplicationCreateRequest request = new ApplicationCreateRequest();
        request.setRequestedAmount("10000.00");
        assertEquals("10000.00", request.getRequestedAmount(), "Requested Amount should be '10000.00'");
    }

    /**
     * Test for the 'projectDescription' field.
     */
    @Test
    void testProjectDescription() {
        ApplicationCreateRequest request = new ApplicationCreateRequest();
        request.setProjectDescription("A sustainable energy project.");
        assertEquals("A sustainable energy project.", request.getProjectDescription(), "Project Description should be 'A sustainable energy project.'");
    }
}