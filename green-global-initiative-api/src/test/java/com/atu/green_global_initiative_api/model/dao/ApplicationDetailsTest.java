package com.atu.green_global_initiative_api.model.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationDetailsTest {
    private ApplicationDetails applicationDetails;

    @BeforeEach
    void setUp() {
        applicationDetails = new ApplicationDetails();
    }

    @Test
    void testSetAndGetApplicationId() {
        applicationDetails.setApplicationId(1);
        assertEquals(1, applicationDetails.getApplicationId());
    }

    @Test
    void testSetAndGetOrganizationName() {
        applicationDetails.setOrganizationName("Green Earth Initiative");
        assertEquals("Green Earth Initiative", applicationDetails.getOrganizationName());
    }

    @Test
    void testSetAndGetApplicationStatus() {
        applicationDetails.setApplicationStatus("Approved");
        assertEquals("Approved", applicationDetails.getApplicationStatus());
    }

    @Test
    void testSetAndGetApprovalDate() {
        applicationDetails.setApprovalDate("2025-03-20");
        assertEquals("2025-03-20", applicationDetails.getApprovalDate());
    }

    @Test
    void testSetAndGetRequestedAmount() {
        applicationDetails.setRequestedAmount("10000");
        assertEquals("10000", applicationDetails.getRequestedAmount());
    }

    @Test
    void testSetAndGetProjectDescription() {
        applicationDetails.setProjectDescription("Renewable energy initiative");
        assertEquals("Renewable energy initiative", applicationDetails.getProjectDescription());
    }

    @Test
    void testSetAndGetAdminComments() {
        applicationDetails.setAdminComments("Approved with conditions");
        assertEquals("Approved with conditions", applicationDetails.getAdminComments());
    }

    @Test
    void testSetAndGetUserDetails() {
        UserDetails userDetails = new UserDetails();
        applicationDetails.setUserDetails(userDetails);
        assertEquals(userDetails, applicationDetails.getUserDetails());
    }

    @Test
    void testSetAndGetGrants() {
        Grants grants = new Grants();
        applicationDetails.setGrants(grants);
        assertEquals(grants, applicationDetails.getGrants());
    }
}