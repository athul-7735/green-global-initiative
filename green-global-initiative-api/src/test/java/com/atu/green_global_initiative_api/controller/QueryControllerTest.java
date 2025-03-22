package com.atu.green_global_initiative_api.controller;

import com.atu.green_global_initiative_api.model.dao.ContactUs;
import com.atu.green_global_initiative_api.service.QueryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QueryControllerTest {

    @Mock
    private QueryServiceImpl queryService;

    @InjectMocks
    private QueryController queryController;

    private ContactUs contactUs;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        contactUs = new ContactUs();
        contactUs.setQueryId(1);
        contactUs.setName("Test User");
        contactUs.setEmail("test@example.com");
        contactUs.setPhone("988765432");
        contactUs.setMessage("This is a test query.");
    }

    @Test
    void testCreateQuery_Success() {
        // Arrange
        when(queryService.createQuery(any(ContactUs.class))).thenReturn(contactUs);

        // Act
        ContactUs response = queryController.createQuery(contactUs);

        // Assert
        assertNotNull(response);
        assertEquals("Test User", response.getName());
        assertEquals("test@example.com", response.getEmail());
        assertEquals("This is a test query.", response.getMessage());

        verify(queryService, times(1)).createQuery(any(ContactUs.class));
    }

//    @Test
//    void testCreateQuery_Exception() {
//        // Arrange
//        when(queryService.createQuery(any(ContactUs.class))).thenThrow(new RuntimeException("Database error"));
//
//        // Act
//        ContactUs response = queryController.createQuery(contactUs);
//
//        // Assert
//        assertNotNull(response); // Ensure a response is returned
//        assertNull(response.getQueryId()); // Since the exception is caught, the returned object should be empty
//        assertNull(response.getName());
//        assertNull(response.getEmail());
//        assertNull(response.getMessage());
//
//        verify(queryService, times(1)).createQuery(any(ContactUs.class));
//    }
}