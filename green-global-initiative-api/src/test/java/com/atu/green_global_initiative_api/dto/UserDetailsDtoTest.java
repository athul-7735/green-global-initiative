package com.atu.green_global_initiative_api.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDetailsDtoTest {

    @Test
    void testUserId() {
        UserDetailsDto user = new UserDetailsDto();
        user.setUserId(1);
        assertEquals(1, user.getUserId(), "User ID should be 1");
    }

    @Test
    void testFirstName() {
        UserDetailsDto user = new UserDetailsDto();
        user.setFirstName("John");
        assertEquals("John", user.getFirstName(), "First Name should be 'John'");
    }

    @Test
    void testLastName() {
        UserDetailsDto user = new UserDetailsDto();
        user.setLastName("Doe");
        assertEquals("Doe", user.getLastName(), "Last Name should be 'Doe'");
    }

    @Test
    void testEmail() {
        UserDetailsDto user = new UserDetailsDto();
        user.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", user.getEmail(), "Email should be 'john.doe@example.com'");
    }

    @Test
    void testIsAdmin() {
        UserDetailsDto user = new UserDetailsDto();
        user.setAdmin(true);
        assertTrue(user.getIsAdmin(), "User should be an admin");
    }

    @Test
    void testLastLogin() {
        UserDetailsDto user = new UserDetailsDto();
        user.setLastLogin("2025-03-19T14:00:00");
        assertEquals("2025-03-19T14:00:00", user.getLastLogin(), "Last login timestamp should be '2025-03-19T14:00:00'");
    }

}