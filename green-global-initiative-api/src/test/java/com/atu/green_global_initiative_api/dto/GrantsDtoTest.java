package com.atu.green_global_initiative_api.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrantsDtoTest {

    @Test
    void testGrantId() {
        GrantsDto grant = new GrantsDto();
        grant.setGrantId(1);
        assertEquals(1, grant.getGrantId(), "Grant ID should be 1");
    }

    /**
     * Test for 'grantName'.
     */
    @Test
    void testGrantName() {
        GrantsDto grant = new GrantsDto();
        grant.setGrantName("GreenTech Innovation");
        assertEquals("GreenTech Innovation", grant.getGrantName(), "Grant Name should be 'GreenTech Innovation'");
    }

    /**
     * Test for 'amount'.
     */
    @Test
    void testAmount() {
        GrantsDto grant = new GrantsDto();
        grant.setAmount("50000");
        assertEquals("50000", grant.getAmount(), "Amount should be '50000'");
    }

    /**
     * Test for 'description'.
     */
    @Test
    void testDescription() {
        GrantsDto grant = new GrantsDto();
        grant.setDescription("Grant to support sustainable energy projects.");
        assertEquals("Grant to support sustainable energy projects.", grant.getDescription(), "Description should be 'Grant to support sustainable energy projects.'");
    }

    /**
     * Test for 'eligibility'.
     */
    @Test
    void testEligibility() {
        GrantsDto grant = new GrantsDto();
        grant.setEligibility("Open to non-profit organizations with a green initiative.");
        assertEquals("Open to non-profit organizations with a green initiative.", grant.getEligibility(), "Eligibility should be 'Open to non-profit organizations with a green initiative.'");
    }
}