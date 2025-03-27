package com.atu.green_global_initiative_api.controller;

import com.atu.green_global_initiative_api.dto.ApplicationDetailsDto;
import com.atu.green_global_initiative_api.dto.UserDetailsDto;
import com.atu.green_global_initiative_api.model.dao.request.ApplicationCreateRequest;
import com.atu.green_global_initiative_api.model.dao.request.ApplicationUpdateRequest;
import com.atu.green_global_initiative_api.service.GrantsApplicationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GrantsApplicationControllerTest {

    @InjectMocks
    private GrantsApplicationController grantsApplicationController;

    @Mock
    private GrantsApplicationServiceImpl grantsApplicationService;

    private UserDetailsDto userDetailsDto1;
    private UserDetailsDto userDetailsDto2;
    private ApplicationDetailsDto application1;
    private ApplicationDetailsDto application2;
    private ApplicationCreateRequest applicationCreateRequest;
    private ApplicationUpdateRequest applicationUpdateRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userDetailsDto1 = new UserDetailsDto();
        userDetailsDto2 = new UserDetailsDto();
        userDetailsDto1.setEmail("athuls@gmail.com");
        userDetailsDto1.setUserId(1);
        userDetailsDto1.setFirstName("Athul");
        userDetailsDto1.setLastName("S");
        userDetailsDto1.setLastLogin(new Date().toString());
        userDetailsDto1.setAdmin(false);
        userDetailsDto1.setApplicationDetails(null);
        userDetailsDto2.setEmail("helena@gmail.com");
        userDetailsDto2.setUserId(2);
        userDetailsDto2.setFirstName("Helena");
        userDetailsDto2.setLastName("G");
        userDetailsDto2.setLastLogin(new Date().toString());
        userDetailsDto2.setAdmin(false);
        userDetailsDto2.setApplicationDetails(null);

        application1 = new ApplicationDetailsDto();
        application1.setApplicationId(1);
        application1.setUserDetailsDto(userDetailsDto1);
        application1.setApplicationStatus("Pending");

        application2 = new ApplicationDetailsDto();
        application2.setApplicationId(2);
        application2.setUserDetailsDto(userDetailsDto2);
        application2.setApplicationStatus("Approved");

        applicationCreateRequest = new ApplicationCreateRequest();
        applicationCreateRequest.setApplicationId(1);
        applicationCreateRequest.setApplicationStatus("In Progress");
        applicationCreateRequest.setGrantId(1);
        applicationCreateRequest.setUserId(1);
        applicationCreateRequest.setRequestedAmount("1000");
        applicationCreateRequest.setOrganizationName("ATU");
        applicationCreateRequest.setProjectDescription("The project helps in utilizing the funds for new Initiatives");

        applicationUpdateRequest = new ApplicationUpdateRequest();
        applicationUpdateRequest.setAdminComments("Approved");
        applicationUpdateRequest.setOrganizationName("ATU");
        applicationUpdateRequest.setApplicationId(1);
        applicationUpdateRequest.setApplicationStatus("Approved");
    }

    @Test
    void testGetAllApplications_Success() {
        // Arrange
        List<ApplicationDetailsDto> mockApplications = List.of(application1, application2);
        when(grantsApplicationService.getAllApplicationDetails()).thenReturn(mockApplications);

        // Act
        ResponseEntity<List<ApplicationDetailsDto>> response = grantsApplicationController.getAllApplications();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("athuls@gmail.com", response.getBody().get(0).getUserDetailsDto().getEmail());

        verify(grantsApplicationService, times(1)).getAllApplicationDetails();
    }

    @Test
    void testGetAllApplications_EmptyList() {
        // Arrange
        when(grantsApplicationService.getAllApplicationDetails()).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<ApplicationDetailsDto>> response = grantsApplicationController.getAllApplications();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());

        verify(grantsApplicationService, times(1)).getAllApplicationDetails();
    }

    @Test
    void testGetApplicationById_Success() {
        // Arrange
        List<ApplicationDetailsDto> mockApplication = List.of(application1);
        when(grantsApplicationService.getAllApplicationDetailsByApplicationId("A001")).thenReturn(mockApplication);

        // Act
        ResponseEntity<List<ApplicationDetailsDto>> response = grantsApplicationController.getApplicationById("A001");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("athuls@gmail.com", response.getBody().get(0).getUserDetailsDto().getEmail());
        verify(grantsApplicationService, times(1)).getAllApplicationDetailsByApplicationId("A001");
    }

    @Test
    void testCreateApplication_Success() {
        // Arrange
        List<ApplicationDetailsDto> mockResponse = List.of(application1);
        when(grantsApplicationService.createApplicationDetails(applicationCreateRequest)).thenReturn(mockResponse);

        // Act
        ApplicationDetailsDto response = grantsApplicationController.createApplication(applicationCreateRequest);

        // Assert
        assertNotNull(response);
        assertEquals(1, response.getApplicationId());

        verify(grantsApplicationService, times(1)).createApplicationDetails(applicationCreateRequest);
    }

    @Test
    void testUpdateApplication_Success() {
        // Arrange
        List<ApplicationDetailsDto> mockResponse = List.of(application1);
        when(grantsApplicationService.updateApplicationDetails(applicationUpdateRequest)).thenReturn(mockResponse);

        // Act
        ResponseEntity<ApplicationDetailsDto> response = grantsApplicationController.updateApplication(applicationUpdateRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getApplicationId());

        verify(grantsApplicationService, times(1)).updateApplicationDetails(applicationUpdateRequest);
    }

    @Test
    void testUpdateApplication_InvalidStatus() {
        // Arrange
        applicationUpdateRequest.setApplicationStatus("InvalidStatus");

        // Act
        ResponseEntity<ApplicationDetailsDto> response = grantsApplicationController.updateApplication(applicationUpdateRequest);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());

        verify(grantsApplicationService, never()).updateApplicationDetails(applicationUpdateRequest);
    }
}