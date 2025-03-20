package com.atu.green_global_initiative_api.service;

import com.atu.green_global_initiative_api.dto.GrantsDto;
import com.atu.green_global_initiative_api.model.dao.Grants;
import com.atu.green_global_initiative_api.repository.ContactUsRepo;
import com.atu.green_global_initiative_api.repository.GrantsRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GrantsServiceImplTest {

    @Mock
    private GrantsRepo grantsRepo;

    @InjectMocks
    private GrantsServiceImpl grantsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllGrants_EmptyList() {
        // Given: When no grants are available
        when(grantsRepo.findAll()).thenReturn(Collections.emptyList());

        // When: Call the method
        List<GrantsDto> result = grantsService.getAllGrants();

        // Then: Verify the result is empty
        assertNotNull(result, "Result should not be null");
        assertTrue(result.isEmpty(), "Result list should be empty");

        // Verify the repository method was called
        verify(grantsRepo, times(1)).findAll();
    }

    @Test
    void testGetAllGrants_ValidData() {
        // Given: When there are valid grants in the database
        Grants grant1 = new Grants();
        grant1.setGrantName("Teto Grant");
        grant1.setAmount("1000");
        grant1.setDescription("Climate Change Grant");
        grant1.setEligibility("NA");
        grant1.setGrantId(1);
        Grants grant2 = new Grants();
        grant1.setGrantName("Pejito Grant");
        grant1.setAmount("2000");
        grant1.setDescription("Climate Change Grant");
        grant1.setEligibility("NA");
        grant1.setGrantId(2);
        List<Grants> grantsList = Arrays.asList(grant1, grant2);

        when(grantsRepo.findAll()).thenReturn(grantsList);

        // When: Call the method
        List<GrantsDto> result = grantsService.getAllGrants();

        // Then: Verify the result contains the correct number of elements
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "Result list should contain 2 items");

        // Check the values of the DTOs
        GrantsDto dto1 = result.get(0);
        assertEquals(grant1.getGrantId(), dto1.getGrantId(), "Grant ID should match");
        assertEquals(grant1.getGrantName(), dto1.getGrantName(), "Grant Name should match");
        assertEquals(grant1.getAmount(), dto1.getAmount(), "Grant Amount should match");

        GrantsDto dto2 = result.get(1);
        assertEquals(grant2.getGrantId(), dto2.getGrantId(), "Grant ID should match");
        assertEquals(grant2.getGrantName(), dto2.getGrantName(), "Grant Name should match");
        assertEquals(grant2.getAmount(), dto2.getAmount(), "Grant Amount should match");

        // Verify the repository method was called
        verify(grantsRepo, times(1)).findAll();
    }
//
    @Test
    void testGetAllGrants_NullList() {
        // Given: When the repository returns null
        when(grantsRepo.findAll()).thenReturn(new ArrayList<>());

        // When: Call the method
        List<GrantsDto> result = grantsService.getAllGrants();

        // Then: Verify the result is empty
        assertNotNull(result, "Result should not be null");
        assertTrue(result.isEmpty(), "Result list should be empty");

        // Verify the repository method was called
        verify(grantsRepo, times(1)).findAll();
    }
}