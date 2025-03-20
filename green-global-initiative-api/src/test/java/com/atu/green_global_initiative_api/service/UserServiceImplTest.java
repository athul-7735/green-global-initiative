package com.atu.green_global_initiative_api.service;

import com.atu.green_global_initiative_api.dto.UserDetailsDto;
import com.atu.green_global_initiative_api.model.dao.UserDetails;
import com.atu.green_global_initiative_api.repository.UserDetailsRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserDetailsRepo userDetailsRepo;

    @InjectMocks()
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl();

        // Inject mock repository using reflection
        try {
            java.lang.reflect.Field repoField = UserServiceImpl.class.getDeclaredField("userDetailsRepo");
            repoField.setAccessible(true);
            repoField.set(userService, userDetailsRepo);
        } catch (Exception e) {
            throw new RuntimeException("Failed to inject mock repository", e);
        }
    }

    @Test
    void testGetAllUserDetails_ShouldReturnUserList() {
        // Given
        UserDetails user1 = new UserDetails();
        user1.setUserId(1);
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setEmail("john.doe@example.com");

        UserDetails user2 = new UserDetails();
        user2.setUserId(2);
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.setEmail("jane.doe@example.com");

        List<UserDetails> users = Arrays.asList(user1, user2);
        when(userDetailsRepo.findAll()).thenReturn(users);

        // When
        List<UserDetailsDto> result = userService.getAllUserDetails();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getFirstName());
        assertEquals("Jane", result.get(1).getFirstName());
        verify(userDetailsRepo, times(1)).findAll();
    }

    @Test
    void testGetAllUserDetailsById_ShouldReturnUserDetails() {
        // Given
        UserDetails user = new UserDetails();
        user.setUserId(1);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");

        List<UserDetails> userList = List.of(user);
        when(userDetailsRepo.findAllById(1)).thenReturn(userList);

        // When
        UserDetailsDto result = userService.getAllUserDetailsById(1);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getUserId());
        assertEquals("John", result.getFirstName());
        verify(userDetailsRepo, times(1)).findAllById(1);
    }

    @Test
    void testGetAllUserDetailsByEmail_ShouldReturnUserDetails() {
        // Given
        UserDetails user = new UserDetails();
        user.setUserId(1);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");

        List<UserDetails> userList = List.of(user);
        when(userDetailsRepo.findAllByEmail("john.doe@example.com")).thenReturn(userList);

        // When
        UserDetailsDto result = userService.getAllUserDetailsByEmail("john.doe@example.com");

        // Then
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        verify(userDetailsRepo, times(1)).findAllByEmail("john.doe@example.com");
    }

    @Test
    void testUserSignUp_ShouldSaveUser() {
        // Given
        UserDetails user = new UserDetails();
        user.setUserId(1);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");

        when(userDetailsRepo.save(any(UserDetails.class))).thenReturn(user);

        // When
        UserDetailsDto result = userService.userSignUp(user);

        // Then
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        verify(userDetailsRepo, times(1)).save(user);
    }

    @Test
    void testAuthenticateUser_ShouldReturnUserDetailsWhenValid() {
        // Given
        UserDetails user = new UserDetails();
        user.setUserId(1);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password123");

        List<UserDetails> userList = List.of(user);
        when(userDetailsRepo.findAllByEmail("john.doe@example.com")).thenReturn(userList);

        // When
        UserDetailsDto result = userService.authenticateUser("john.doe@example.com", "password123");

        // Then
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        verify(userDetailsRepo, times(1)).findAllByEmail("john.doe@example.com");
    }

    @Test
    void testAuthenticateUser_ShouldReturnNullForInvalidPassword() {
        // Given
        UserDetails user = new UserDetails();
        user.setUserId(1);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password123");

        List<UserDetails> userList = List.of(user);
        when(userDetailsRepo.findAllByEmail("john.doe@example.com")).thenReturn(userList);

        // When
        UserDetailsDto result = userService.authenticateUser("john.doe@example.com", "wrongpassword");

        // Then
        assertNull(result);
        verify(userDetailsRepo, times(1)).findAllByEmail("john.doe@example.com");
    }

    @Test
    void testAuthenticateUser_ShouldReturnNullForNonExistentUser() {
        // Given
        when(userDetailsRepo.findAllByEmail("unknown@example.com")).thenReturn(Collections.emptyList());

        // When
        UserDetailsDto result = userService.authenticateUser("unknown@example.com", "password");

        // Then
        assertNull(result);
        verify(userDetailsRepo, times(1)).findAllByEmail("unknown@example.com");
    }
}