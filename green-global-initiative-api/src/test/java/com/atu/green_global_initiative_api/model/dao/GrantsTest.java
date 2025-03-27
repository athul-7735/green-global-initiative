package com.atu.green_global_initiative_api.model.dao;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GrantsTest {

    @Test
    void testGrantsEntity() {
        // Create a Grants object
        Grants grant = new Grants();

        // Set values
        grant.setGrantId(1);
        grant.setGrantName("Climate Change Initiative");
        grant.setAmount("5000");
        grant.setDescription("Funding for environmental projects");
        grant.setEligibility("Organizations working on climate change");

        List<ApplicationDetails> applications = new ArrayList<>();
        grant.setApplicationDetails(applications);

        // Assertions
        assertThat(grant.getGrantId()).isEqualTo(1);
        assertThat(grant.getGrantName()).isEqualTo("Climate Change Initiative");
        assertThat(grant.getAmount()).isEqualTo("5000");
        assertThat(grant.getDescription()).isEqualTo("Funding for environmental projects");
        assertThat(grant.getEligibility()).isEqualTo("Organizations working on climate change");
        assertThat(grant.getApplicationDetails()).isEmpty();
    }

}