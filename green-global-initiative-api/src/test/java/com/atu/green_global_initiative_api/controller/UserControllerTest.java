package com.atu.green_global_initiative_api.controller;

import com.atu.green_global_initiative_api.dto.UserDetailsDto;
import com.atu.green_global_initiative_api.model.dao.UserDetails;
import com.atu.green_global_initiative_api.model.dao.request.LoginRequest;
import com.atu.green_global_initiative_api.security.JwtUtil;
import com.atu.green_global_initiative_api.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;

class UserControllerTest {
        @Mock
        private UserServiceImpl userServiceImpl;

        @Mock
        private JwtUtil jwtUtil;

        @InjectMocks
        private UserController userController;

        private UserDetailsDto userDetailsDto;
        private UserDetails userDetails;
        private LoginRequest loginRequest;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
            userDetailsDto = new UserDetailsDto();
            userDetailsDto.setEmail("test@example.com");
            userDetailsDto.setFirstName("Test");

            userDetails = new UserDetails();
            userDetails.setEmail("test@example.com");
            userDetails.setFirstName("Test");

            loginRequest = new LoginRequest();
            loginRequest.setEmail("test@example.com");
            loginRequest.setPassword("password123");
        }

        @Test
        void testGetAllUsers_Success() {
            // Arrange
            List<UserDetailsDto> users = List.of(userDetailsDto);
            when(userServiceImpl.getAllUserDetails()).thenReturn(users);

            // Act
            ResponseEntity<List<UserDetailsDto>> response = userController.getAllUsers();

            // Assert
            assertEquals(OK, response.getStatusCode());
            assertNotNull(response.getBody());
            assertEquals(1, response.getBody().size());
            assertEquals("test@example.com", response.getBody().get(0).getEmail());

            verify(userServiceImpl, times(1)).getAllUserDetails();
        }

        @Test
        void testGetAllUsers_EmptyList() {
            // Arrange
            when(userServiceImpl.getAllUserDetails()).thenReturn(Collections.emptyList());

            // Act
            ResponseEntity<List<UserDetailsDto>> response = userController.getAllUsers();

            // Assert
            assertEquals(NOT_FOUND, response.getStatusCode());
            assertNotNull(response.getBody());
            assertTrue(response.getBody().isEmpty());

            verify(userServiceImpl, times(1)).getAllUserDetails();
        }

        @Test
        void testCreateUser_Success() {
            // Arrange
            when(userServiceImpl.userSignUp(any(UserDetails.class))).thenReturn(userDetailsDto);

            // Act
            UserDetailsDto response = userController.createUser(userDetails);

            // Assert
            assertNotNull(response);
            assertEquals("test@example.com", response.getEmail());

            verify(userServiceImpl, times(1)).userSignUp(any(UserDetails.class));
        }

        @Test
        void testLoginUser_Success() {
            // Arrange
            when(userServiceImpl.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword())).thenReturn(userDetailsDto);
            when(jwtUtil.generateToken(userDetailsDto)).thenReturn("mocked_jwt_token");

            // Act
            ResponseEntity<Map<String, String>> response = userController.loginUser(loginRequest);

            // Assert
            assertEquals(OK, response.getStatusCode());
            assertNotNull(response.getBody());
            assertEquals("mocked_jwt_token", response.getBody().get("token"));

            verify(userServiceImpl, times(1)).authenticateUser(anyString(), anyString());
            verify(jwtUtil, times(1)).generateToken(any(UserDetailsDto.class));
        }

        @Test
        void testLoginUser_Failure() {
            // Arrange
            when(userServiceImpl.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword())).thenReturn(null);

            // Act
            ResponseEntity<Map<String, String>> response = userController.loginUser(loginRequest);

            // Assert
            assertEquals(UNAUTHORIZED, response.getStatusCode());
            assertNull(response.getBody());

            verify(userServiceImpl, times(1)).authenticateUser(anyString(), anyString());
            verify(jwtUtil, never()).generateToken(any(UserDetailsDto.class));
        }
}