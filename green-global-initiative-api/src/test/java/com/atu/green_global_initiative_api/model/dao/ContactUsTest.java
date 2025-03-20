package com.atu.green_global_initiative_api.model.dao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactUsTest {

    @Test
    void testGettersAndSetters() {
        ContactUs contact = new ContactUs();

        contact.setQueryId(1);
        contact.setName("John Doe");
        contact.setEmail("johndoe@example.com");
        contact.setPhone("1234567890");
        contact.setMessage("I need more details about the grants.");

        assertEquals(1, contact.getQueryId());
        assertEquals("John Doe", contact.getName());
        assertEquals("johndoe@example.com", contact.getEmail());
        assertEquals("1234567890", contact.getPhone());
        assertEquals("I need more details about the grants.", contact.getMessage());
    }

    @Test
    void testEmptyValues() {
        ContactUs contact = new ContactUs();

        contact.setQueryId(0);
        contact.setName("");
        contact.setEmail("");
        contact.setPhone("");
        contact.setMessage("");

        assertEquals(0, contact.getQueryId());
        assertEquals("", contact.getName());
        assertEquals("", contact.getEmail());
        assertEquals("", contact.getPhone());
        assertEquals("", contact.getMessage());
    }

    @Test
    void testNullValues() {
        ContactUs contact = new ContactUs();

        contact.setQueryId(2);
        contact.setName(null);
        contact.setEmail(null);
        contact.setPhone(null);
        contact.setMessage(null);

        assertEquals(2, contact.getQueryId());
        assertNull(contact.getName());
        assertNull(contact.getEmail());
        assertNull(contact.getPhone());
        assertNull(contact.getMessage());
    }

    @Test
    void testLargeInputValues() {
        ContactUs contact = new ContactUs();

        String longText = "A".repeat(1000);

        contact.setQueryId(3);
        contact.setName(longText);
        contact.setEmail(longText + "@example.com");
        contact.setPhone("9".repeat(20));
        contact.setMessage(longText);

        assertEquals(3, contact.getQueryId());
        assertEquals(longText, contact.getName());
        assertEquals(longText + "@example.com", contact.getEmail());
        assertEquals("9".repeat(20), contact.getPhone());
        assertEquals(longText, contact.getMessage());
    }
}