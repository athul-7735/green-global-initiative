package com.atu.green_global_initiative_api.controller;

import com.atu.green_global_initiative_api.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class GlobalExceptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void testHandleResourceNotFound() throws Exception {
        // Call an actual endpoint that throws ResourceNotFoundException
        mockMvc.perform(get("/api/void") // Ensure this matches your real API path
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @InjectMocks
    private GlobalExceptionController globalExceptionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleResourceNotFound_ShouldReturnNotFoundResponse() {
        // Given
        String errorMessage = "Test Resource Not Found";
        ResourceNotFoundException exception = new ResourceNotFoundException(errorMessage);

        // When
        ResponseEntity<HashMap<String, Object>> response = globalExceptionController.handleResourceNotFound(exception);

        // Then
        assertNotNull(response);
        assertEquals(String.valueOf(HttpStatus.NOT_FOUND.value()), String.valueOf(response.getStatusCode().value()),"HTTP status should be 404");


        HashMap<String, Object> responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(String.valueOf(HttpStatus.NOT_FOUND.value()), String.valueOf(responseBody.get("status")),"HTTP status should be 404");
        assertEquals("Resource Not Found", responseBody.get("error"));
        assertEquals(errorMessage, responseBody.get("message"));
        assertNotNull(responseBody.get("timestamp"));
        assertTrue(responseBody.get("timestamp") instanceof LocalDateTime);
    }
}