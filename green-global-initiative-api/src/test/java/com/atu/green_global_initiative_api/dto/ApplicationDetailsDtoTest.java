package com.atu.green_global_initiative_api.dto;

import com.atu.green_global_initiative_api.model.dao.Grants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationDetailsDtoTest {

    @Test
    void testApplicationId() {
        ApplicationDetailsDto applicationDetailsDto = new ApplicationDetailsDto();
        applicationDetailsDto.setApplicationId(101);
        assertEquals(101, applicationDetailsDto.getApplicationId(), "Application ID should be 101");
    }

    /**
     * Test for the 'organizationName' field.
     */
    @Test
    void testOrganizationName() {
        ApplicationDetailsDto applicationDetailsDto = new ApplicationDetailsDto();
        applicationDetailsDto.setOrganizationName("GreenTech Inc.");
        assertEquals("GreenTech Inc.", applicationDetailsDto.getOrganizationName(), "Organization Name should be 'GreenTech Inc.'");
    }

    /**
     * Test for the 'applicationStatus' field.
     */
    @Test
    void testApplicationStatus() {
        ApplicationDetailsDto applicationDetailsDto = new ApplicationDetailsDto();
        applicationDetailsDto.setApplicationStatus("Approved");
        assertEquals("Approved", applicationDetailsDto.getApplicationStatus(), "Application Status should be 'Approved'");
    }

    /**
     * Test for the 'userDetailsDto' field.
     */
    @Test
    void testUserDetailsDto() {
        ApplicationDetailsDto applicationDetailsDto = new ApplicationDetailsDto();
        UserDetailsDto userDetails = new UserDetailsDto();
        userDetails.setFirstName("John Doe");
        applicationDetailsDto.setUserDetailsDto(userDetails);

        assertEquals("John Doe", applicationDetailsDto.getUserDetailsDto().getFirstName(), "John Doe");
    }

    /**
     * Test for the 'grants' field.
     */
    @Test
    void testGrants() {
        ApplicationDetailsDto applicationDetailsDto = new ApplicationDetailsDto();
        Grants grants = new Grants();
        grants.setGrantId(1);
        applicationDetailsDto.setGrants(grants);

        assertEquals(1, applicationDetailsDto.getGrants().getGrantId(), "Grant ID should be 1");
    }

    /**
     * Test for the 'approvalDate' field.
     */
    @Test
    void testApprovalDate() {
        ApplicationDetailsDto applicationDetailsDto = new ApplicationDetailsDto();
        applicationDetailsDto.setApprovalDate("2025-05-01");
        assertEquals("2025-05-01", applicationDetailsDto.getApprovalDate(), "Approval Date should be '2025-05-01'");
    }

    /**
     * Test for the 'requestedAmount' field.
     */
    @Test
    void testRequestedAmount() {
        ApplicationDetailsDto applicationDetailsDto = new ApplicationDetailsDto();
        applicationDetailsDto.setRequestedAmount("50000");
        assertEquals("50000", applicationDetailsDto.getRequestedAmount(), "Requested Amount should be '50000'");
    }

    /**
     * Test for the 'projectDescription' field.
     */
    @Test
    void testProjectDescription() {
        ApplicationDetailsDto applicationDetailsDto = new ApplicationDetailsDto();
        applicationDetailsDto.setProjectDescription("Project to develop green energy solutions.");
        assertEquals("Project to develop green energy solutions.", applicationDetailsDto.getProjectDescription(), "Project Description should be 'Project to develop green energy solutions.'");
    }

    /**
     * Test for the 'adminComments' field.
     */
    @Test
    void testAdminComments() {
        ApplicationDetailsDto applicationDetailsDto = new ApplicationDetailsDto();
        applicationDetailsDto.setAdminComments("Awaiting final approval.");
        assertEquals("Awaiting final approval.", applicationDetailsDto.getAdminComments(), "Admin Comments should be 'Awaiting final approval.'");
    }

}