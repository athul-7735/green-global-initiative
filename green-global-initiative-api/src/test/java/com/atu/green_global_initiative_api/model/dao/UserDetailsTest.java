package com.atu.green_global_initiative_api.model.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDetailsTest {

    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        userDetails = new UserDetails();
    }

    @Test
    void testUserId() {
        userDetails.setUserId(1);
        assertEquals(1, userDetails.getUserId());
    }

    @Test
    void testFirstName() {
        userDetails.setFirstName("John");
        assertEquals("John", userDetails.getFirstName());
    }

    @Test
    void testLastName() {
        userDetails.setLastName("Doe");
        assertEquals("Doe", userDetails.getLastName());
    }

    @Test
    void testEmail() {
        userDetails.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", userDetails.getEmail());
    }

    @Test
    void testPassword() {
        userDetails.setPassword("securePassword123");
        assertEquals("securePassword123", userDetails.getPassword());
    }

    @Test
    void testIsAdmin() {
        userDetails.setAdmin(true);
        assertTrue(userDetails.isAdmin());
    }

    @Test
    void testLastLogin() {
        userDetails.setLastLogin("2025-03-20 10:00:00");
        assertEquals("2025-03-20 10:00:00", userDetails.getLastLogin());
    }

    @Test
    void testApplicationDetailsList() {
        List<ApplicationDetails> applications = new ArrayList<>();
        ApplicationDetails app1 = new ApplicationDetails();
        app1.setApplicationId(100);
        app1.setOrganizationName("TechOrg");
        applications.add(app1);

        userDetails.setApplicationDetails(applications);
        assertNotNull(userDetails.getApplicationDetails());
        assertEquals(1, userDetails.getApplicationDetails().size());
        assertEquals("TechOrg", userDetails.getApplicationDetails().get(0).getOrganizationName());
    }
}