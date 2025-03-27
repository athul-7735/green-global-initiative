package com.atu.green_global_initiative_api.service;

import com.atu.green_global_initiative_api.model.dao.ContactUs;
import com.atu.green_global_initiative_api.repository.ContactUsRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class QueryServiceImplTest {

    @Mock
    private ContactUsRepo contactUsRepo;

    @InjectMocks
    private QueryServiceImpl queryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        queryService = new QueryServiceImpl();

        // Inject mock repository using reflection (if using field injection)
        try {
            java.lang.reflect.Field repoField = QueryServiceImpl.class.getDeclaredField("contactUsRepo");
            repoField.setAccessible(true);
            repoField.set(queryService, contactUsRepo);
        } catch (Exception e) {
            throw new RuntimeException("Failed to inject mock repository", e);
        }
    }

    @Test
    void testCreateQuery() {
        // Given
        ContactUs contactUs = new ContactUs();
        contactUs.setQueryId(1);
        contactUs.setName("John Doe");
        contactUs.setEmail("john.doe@example.com");
        contactUs.setMessage("Test message");

        when(contactUsRepo.save(any(ContactUs.class))).thenReturn(contactUs);

        // When
        ContactUs result = queryService.createQuery(contactUs);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getQueryId());
        assertEquals("John Doe", result.getName());
        verify(contactUsRepo, times(1)).save(any(ContactUs.class));
    }

    @Test
    void testGetAllQueries_ShouldReturnEmptyList() {
        // When
        List<ContactUs> result = queryService.getAllQueries();

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetQueryById_ShouldReturnEmptyList() {
        // When
        List<ContactUs> result = queryService.getQueryById("1");

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

}