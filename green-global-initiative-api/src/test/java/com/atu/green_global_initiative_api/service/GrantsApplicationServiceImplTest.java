package com.atu.green_global_initiative_api.service;

import com.atu.green_global_initiative_api.dto.ApplicationDetailsDto;
import com.atu.green_global_initiative_api.model.dao.ApplicationDetails;
import com.atu.green_global_initiative_api.model.dao.Grants;
import com.atu.green_global_initiative_api.model.dao.UserDetails;
import com.atu.green_global_initiative_api.model.dao.request.ApplicationCreateRequest;
import com.atu.green_global_initiative_api.model.dao.request.ApplicationUpdateRequest;
import com.atu.green_global_initiative_api.repository.ApplicationDetailsRepo;
import com.atu.green_global_initiative_api.repository.GrantsRepo;
import com.atu.green_global_initiative_api.repository.UserDetailsRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import static org.mockito.Mockito.when;

class GrantsApplicationServiceImplTest {
    @Mock
    private ApplicationDetailsRepo applicationDetailsRepo;

    @Mock
    private UserDetailsRepo userDetailsRepo;

    @Mock
    private GrantsRepo grantsRepo;

    @InjectMocks
    private GrantsApplicationServiceImpl grantsApplicationService;

    private ApplicationCreateRequest applicationCreateRequest;
    private ApplicationUpdateRequest applicationUpdateRequest;
    private ApplicationDetails applicationDetails;
    private ApplicationDetailsDto applicationDetailsDto;
    private Grants grants;
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize common mock objects for the tests
        applicationCreateRequest = new ApplicationCreateRequest();
        applicationCreateRequest.setApplicationId(8);
        applicationCreateRequest.setOrganizationName("XYZ Organization");
        applicationCreateRequest.setApplicationStatus("PENDING");
        applicationCreateRequest.setApprovalDate("2025-01-01");
        applicationCreateRequest.setRequestedAmount("10000");
        applicationCreateRequest.setProjectDescription("Project XYZ");
        applicationCreateRequest.setUserId(1);
        applicationCreateRequest.setGrantId(1);

        applicationUpdateRequest = new ApplicationUpdateRequest();
        applicationUpdateRequest.setApplicationId(1);
        applicationUpdateRequest.setApplicationStatus("APPROVED");
        applicationUpdateRequest.setAdminComments("Approved by admin");

        applicationDetails = new ApplicationDetails();
        applicationDetails.setApplicationId(1);
        applicationDetails.setOrganizationName("XYZ Organization");
        applicationDetails.setApplicationStatus("PENDING");
        applicationDetails.setApprovalDate("2025-01-01");
        applicationDetails.setRequestedAmount("10000");
        applicationDetails.setProjectDescription("Project XYZ");
        applicationDetails.setGrants(grants);
        applicationDetails.setUserDetails(userDetails);

        applicationDetailsDto = new ApplicationDetailsDto();
        applicationDetailsDto.setApplicationId(1);
        applicationDetailsDto.setOrganizationName("XYZ Organization");
        applicationDetailsDto.setApplicationStatus("PENDING");
        applicationDetailsDto.setApprovalDate("2025-01-01");
        applicationDetailsDto.setRequestedAmount("10000");
        applicationDetailsDto.setProjectDescription("Project XYZ");

        grants = new Grants();
        grants.setGrantId(1);
        grants.setAmount("100");
        grants.setDescription("Teto Grant");
        grants.setEligibility("eligible");
        grants.setGrantName("Teto Grant");

        userDetails = new UserDetails();
        userDetails.setEmail("athul@gmail.com");
        userDetails.setFirstName("Athul");
        userDetails.setLastName("S");
        userDetails.setUserId(1);
        userDetails.setLastLogin("20-03-2025");
        userDetails.setAdmin(true);
        userDetails.setApplicationDetails(List.of(applicationDetails));
    }

    @Test
    void testCreateApplicationDetails() {
        // Mock the behavior of repositories
        when(applicationDetailsRepo.findAllByApplicationId(10)).thenReturn(Collections.emptyList());
        when(userDetailsRepo.findAllById(1)).thenReturn(Collections.singletonList(userDetails));
        when(grantsRepo.findAllById(1)).thenReturn(Collections.singletonList(grants));
//        when(applicationDetailsRepo.save(any(ApplicationDetails.class))).thenReturn(applicationDetails);
        // Mock the save behavior
        when(applicationDetailsRepo.save(any(ApplicationDetails.class))).thenAnswer(invocation -> {
            ApplicationDetails savedApplicationDetails = invocation.getArgument(0);
            savedApplicationDetails.setGrants(grants); // Ensure grants are set
            savedApplicationDetails.setUserDetails(userDetails); // Ensure userDetails are set
            return savedApplicationDetails;  // Return the saved entity
        });

        List<ApplicationDetailsDto> result = grantsApplicationService.createApplicationDetails(applicationCreateRequest);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(8, result.get(0).getApplicationId());

        // Verify the repository methods were called
        verify(applicationDetailsRepo).findAllByApplicationId(8);
        verify(applicationDetailsRepo).save(any(ApplicationDetails.class));
    }

    @Test
    void testCreateApplicationDetails_WhenApplicationExists() {
        // Mock the behavior when application already exists
        when(applicationDetailsRepo.findAllByApplicationId(8)).thenReturn(Collections.singletonList(new ApplicationDetails()));

        List<ApplicationDetailsDto> result = grantsApplicationService.createApplicationDetails(applicationCreateRequest);

        assertNull(result);  // Should return null if application already exists

        // Verify the repository methods were called
        verify(applicationDetailsRepo).findAllByApplicationId(8);
        verify(applicationDetailsRepo, never()).save(any(ApplicationDetails.class)); // Save should not be called
    }

    @Test
    void testGetAllApplicationDetailsByApplicationId() {
        // Mock the behavior of repositories
        ApplicationDetails applicationDetails = new ApplicationDetails();
        applicationDetails.setApplicationId(1);
        applicationDetails.setGrants(grants); // Ensure grants are set
        applicationDetails.setUserDetails(userDetails); // Ensure userDetails are set

        // Mock the repository to return a list containing the applicationDetails object
        when(applicationDetailsRepo.findAllByApplicationId(1)).thenReturn(Collections.singletonList(applicationDetails));

        // Call the service method
        List<ApplicationDetailsDto> result = grantsApplicationService.getAllApplicationDetailsByApplicationId("1");

        // Assert the result is not null and contains the expected number of items
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getApplicationId());

        // Verify repository method was called
        verify(applicationDetailsRepo).findAllByApplicationId(1); // Pass an Integer argument, not a generic any()
    }


    @Test
    void testGetAllApplicationDetails() {
        // Create and set up the application details
        ApplicationDetails applicationDetails = new ApplicationDetails();
        applicationDetails.setApplicationId(1);

        // Initialize Grants and UserDetails objects
        Grants grants = new Grants();
        grants.setGrantId(101); // Example grant ID
        grants.setGrantName("Sample Grant");
        grants.setDescription("This is a sample grant.");
        grants.setAmount("5000");
        grants.setEligibility("Eligibility criteria for the grant.");

        UserDetails userDetails = new UserDetails();
        userDetails.setUserId(1);
        userDetails.setFirstName("John");
        userDetails.setLastName("Doe");
        userDetails.setEmail("john.doe@example.com");

        // Set the grants and userDetails in the applicationDetails
        applicationDetails.setGrants(grants);
        applicationDetails.setUserDetails(userDetails);

        // Mock the behavior of repositories
        when(applicationDetailsRepo.findAll()).thenReturn(Collections.singletonList(applicationDetails));

        List<ApplicationDetailsDto> result = grantsApplicationService.getAllApplicationDetails();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getApplicationId());

        // Verify repository method was called
        verify(applicationDetailsRepo).findAll();
    }

    @Test
    void testUpdateApplicationDetails() {
        // Initialize the ApplicationDetails object
        ApplicationDetails applicationDetails = new ApplicationDetails();
        applicationDetails.setApplicationId(1);

        // Initialize the Grants and UserDetails objects to prevent NPE
        Grants grants = new Grants();
        grants.setGrantId(101); // Example grant ID
        grants.setGrantName("Sample Grant");
        grants.setDescription("This is a sample grant.");
        grants.setAmount("5000");
        grants.setEligibility("Eligibility criteria for the grant.");

        UserDetails userDetails = new UserDetails();
        userDetails.setUserId(1);
        userDetails.setFirstName("John");
        userDetails.setLastName("Doe");
        userDetails.setEmail("john.doe@example.com");

        // Set the grants and userDetails in the applicationDetails object
        applicationDetails.setGrants(grants);
        applicationDetails.setUserDetails(userDetails);

        // Create the ApplicationUpdateRequest object with mock data
        ApplicationUpdateRequest applicationUpdateRequest = new ApplicationUpdateRequest();
        applicationUpdateRequest.setApplicationId(1);
        applicationUpdateRequest.setApplicationStatus("APPROVED");

        // Mock the behavior when the application exists in the repository
        when(applicationDetailsRepo.findAllByApplicationId(1)).thenReturn(Collections.singletonList(applicationDetails));
        when(applicationDetailsRepo.save(any(ApplicationDetails.class))).thenReturn(applicationDetails);

        // Call the service method
        List<ApplicationDetailsDto> result = grantsApplicationService.updateApplicationDetails(applicationUpdateRequest);

        // Assert the result is not null and the application status is updated
        assertNotNull(result);
        assertEquals("APPROVED", result.get(0).getApplicationStatus());

        // Verify repository methods were called
        verify(applicationDetailsRepo).findAllByApplicationId(1);
        verify(applicationDetailsRepo).save(any(ApplicationDetails.class));
    }
}