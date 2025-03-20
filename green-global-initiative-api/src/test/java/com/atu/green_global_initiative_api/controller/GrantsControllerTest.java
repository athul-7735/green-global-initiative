package com.atu.green_global_initiative_api.controller;

import com.atu.green_global_initiative_api.dto.GrantsDto;
import com.atu.green_global_initiative_api.service.GrantsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GrantsControllerTest {

    @InjectMocks
    private GrantsController grantsController;

    @Mock
    private GrantsServiceImpl grantsService;

    private GrantsDto grant1;
    private GrantsDto grant2;

    @BeforeEach
    void setUp() {
        grant1 = new GrantsDto();
        grant1.setGrantId(1);
        grant1.setGrantName("Climate Change Initiative");
        grant1.setAmount("5000");

        grant2 = new GrantsDto();
        grant2.setGrantId(2);
        grant2.setGrantName("Sustainable Energy Program");
        grant2.setAmount("10000");
    }

    @Test
    void testGetAllApplications_Success() {
        // Arrange
        List<GrantsDto> mockGrants = List.of(grant1, grant2);
        when(grantsService.getAllGrants()).thenReturn(mockGrants);

        // Act
        ResponseEntity<List<GrantsDto>> response = grantsController.getAllApplications();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("Climate Change Initiative", response.getBody().get(0).getGrantName());

        verify(grantsService, times(1)).getAllGrants();
    }

    @Test
    void testGetAllApplications_EmptyList() {
        // Arrange
        when(grantsService.getAllGrants()).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<GrantsDto>> response = grantsController.getAllApplications();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());

        verify(grantsService, times(1)).getAllGrants();
    }

}