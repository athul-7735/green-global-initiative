package com.atu.green_global_initiative_api.security;

import com.atu.green_global_initiative_api.dto.UserDetailsDto;
import com.atu.green_global_initiative_api.model.dao.UserDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class JwtUtilTest {

    @Mock
    private Environment env;

    private JwtUtil jwtUtil;
    private final String secret = "test_secret";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(env.getProperty("jwt_secret")).thenReturn(secret);
        jwtUtil = new JwtUtil(env);
    }

    @Test
    void testGenerateToken_ShouldReturnValidToken() {
        // Given
        UserDetailsDto user = new UserDetailsDto();
        user.setUserId(1);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setLastLogin("2025-03-20");
        user.setAdmin(true);
        // When
        String token = jwtUtil.generateToken(user);

        // Then
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void testExtractEmail_ShouldReturnCorrectEmail() {
        // Given
        UserDetailsDto user1 = new UserDetailsDto();
        user1.setUserId(1);
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setEmail("john.doe@example.com");
        user1.setLastLogin("2025-03-20");
        user1.setAdmin(true);

        String token = jwtUtil.generateToken(user1);



        // When
        String extractedEmail = jwtUtil.extractEmail(token);

        // Then
        assertEquals("john.doe@example.com", extractedEmail);
    }

    @Test
    void testValidateToken_ShouldReturnTrueForValidToken() {
        // Given
        UserDetailsDto user = new UserDetailsDto();
        user.setUserId(1);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setLastLogin("2025-03-20");
        user.setAdmin(true);

        String token = jwtUtil.generateToken(user);

        // When
        boolean isValid = jwtUtil.validateToken(token, user);

        // Then
        assertTrue(isValid);
    }

    @Test
    void testValidateToken_ShouldReturnFalseForInvalidUser() {
        // Given
        UserDetailsDto user1 = new UserDetailsDto();
        UserDetailsDto user2 = new UserDetailsDto();
        user1.setUserId(1);
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setEmail("john.doe@example.com");
        user1.setLastLogin("2025-03-20");
        user1.setAdmin(true);

        user2.setUserId(1);
        user2.setFirstName("Soman");
        user2.setLastName("Sekhar");
        user2.setEmail("soman@example.com");
        user2.setLastLogin("2025-03-20");
        user2.setAdmin(true);
        String token = jwtUtil.generateToken(user1);

        // When
        boolean isValid = jwtUtil.validateToken(token, user2);

        // Then
        assertFalse(isValid);
    }

    @Test
    void testIsTokenExpired_ShouldReturnFalseForValidToken() {
        // Given
        UserDetailsDto user = new UserDetailsDto();
        user.setUserId(1);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setLastLogin("2025-03-20");
        user.setAdmin(true);
        String token = jwtUtil.generateToken(user);

        // When
        boolean isExpired = jwtUtil.validateToken(token, user);

        // Then
        assertTrue(isExpired);
    }

//    @Test
//    void testIsTokenExpired_ShouldReturnTrueForExpiredToken() {
//        UserDetailsDto user = new UserDetailsDto();
//        user.setUserId(1);
//        user.setFirstName("John");
//        user.setLastName("Doe");
//        user.setEmail("john.doe@example.com");
//        user.setLastLogin("2025-03-20");
//        user.setAdmin(true);
//        // Given
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("email", "john.doe@example.com");
//        String expiredToken = Jwts.builder()
//                .setClaims(claims)
//                .setSubject("john.doe@example.com")
//                .setIssuedAt(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 11))
//                .setExpiration(new Date(System.currentTimeMillis() - 1000 * 60 * 60))
//                .signWith(SignatureAlgorithm.HS256, secret)
//                .compact();
//
//        // When
//        boolean isExpired = jwtUtil.validateToken(expiredToken, user);
//
//        // Then
//        assertFalse(isExpired);
//    }
}