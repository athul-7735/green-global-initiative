package com.atu.green_global_initiative_api.security;

import com.atu.green_global_initiative_api.dto.UserDetailsDto;
import com.atu.green_global_initiative_api.service.UserServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtFilterTest {

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Mock
    private SecurityContext securityContext;

    @InjectMocks
    private JwtFilter jwtFilter;

    private final String validToken = "valid.jwt.token";
    private final String invalidToken = "invalid.jwt.token";
    private final String email = "user@example.com";
    private UserDetailsDto userDetailsDto;

    @BeforeEach
    void setUp() {
        userDetailsDto = new UserDetailsDto();
        userDetailsDto.setEmail(email);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void testValidJwtTokenAuthentication() throws ServletException, IOException {
        when(request.getHeader("Authorization")).thenReturn("Bearer " + validToken);
        when(jwtUtil.extractEmail(validToken)).thenReturn(email);
        when(userService.getAllUserDetailsByEmail(email)).thenReturn(userDetailsDto);
        when(jwtUtil.validateToken(validToken, userDetailsDto)).thenReturn(true);

        jwtFilter.doFilterInternal(request, response, filterChain);

        verify(jwtUtil, times(1)).extractEmail(validToken);
        verify(jwtUtil, times(1)).validateToken(validToken, userDetailsDto);
        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    void testInvalidJwtToken() throws ServletException, IOException {
        when(request.getHeader("Authorization")).thenReturn("Bearer " + invalidToken);
        when(jwtUtil.extractEmail(invalidToken)).thenReturn(email);
        when(userService.getAllUserDetailsByEmail(email)).thenReturn(userDetailsDto);
        when(jwtUtil.validateToken(invalidToken, userDetailsDto)).thenReturn(false);

        jwtFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void testMissingAuthorizationHeader() throws ServletException, IOException {
        when(request.getHeader("Authorization")).thenReturn(null);

        jwtFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void testMalformedAuthorizationHeader() throws ServletException, IOException {
        when(request.getHeader("Authorization")).thenReturn("Bearer");

        jwtFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }
}