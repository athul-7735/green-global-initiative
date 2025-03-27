package com.atu.green_global_initiative_api.model.dao.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationUpdateRequestTest {

    /**
     * Test for the 'applicationId' field.
     */
    @Test
    void testApplicationId() {
        ApplicationUpdateRequest request = new ApplicationUpdateRequest();
        request.setApplicationId(101);
        assertEquals(101, request.getApplicationId(), "Application ID should be 101");
    }

    /**
     * Test for the 'organizationName' field.
     */
    @Test
    void testOrganizationName() {
        ApplicationUpdateRequest request = new ApplicationUpdateRequest();
        request.setOrganizationName("GreenTech Inc.");
        assertEquals("GreenTech Inc.", request.getOrganizationName(), "Organization Name should be 'GreenTech Inc.'");
    }

    /**
     * Test for the 'applicationStatus' field.
     */
    @Test
    void testApplicationStatus() {
        ApplicationUpdateRequest request = new ApplicationUpdateRequest();
        request.setApplicationStatus("Approved");
        assertEquals("Approved", request.getApplicationStatus(), "Application Status should be 'Approved'");
    }

    /**
     * Test for the 'adminComments' field.
     */
    @Test
    void testAdminComments() {
        ApplicationUpdateRequest request = new ApplicationUpdateRequest();
        request.setAdminComments("Grant approved, awaiting disbursement.");
        assertEquals("Grant approved, awaiting disbursement.", request.getAdminComments(), "Admin Comments should be 'Grant approved, awaiting disbursement.'");
    }
}